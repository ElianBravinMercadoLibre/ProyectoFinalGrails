package proyectofinalgrails

import grails.converters.JSON
import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper

@Transactional
class PedidoService {
    def obtenerJson(String urlInput){
        def url = new URL(urlInput)
        def connection = (HttpURLConnection)url.openConnection()
        connection.setRequestMethod("GET")
        connection.setRequestProperty("Accept", "application/json")
        connection.setRequestProperty("User-Agent", "Mozzilla/5.0")
        JsonSlurper json = new JsonSlurper()
        def lista = json.parse(connection.getInputStream())
        return (lista)
    }

    def getTicketList() {
        def lista
        try {
            lista = this.obtenerJson('https://api.mercadolibre.com/sites/MLA/payment_methods')
        } catch (Exception e) {
            return null
        }
        def listaTickets = []
        lista.each { x ->
            if (x.payment_type_id == "ticket") {
                listaTickets.add(x)
            }
        }
        return listaTickets
    }

    def getLatLon(direccion,localidad,pais){
        def parametrosUbicacion = direccion.replace(" ", "+")
        if(localidad != ""){
            parametrosUbicacion+=",+"+localidad.replace(" ", "+")
        }
        if(pais != ""){
            parametrosUbicacion+=",+"+pais.replace(" ", "+")
        }

        //Obtengo las coordenadas del lugar ingresado usando la api de google geocode
        def lista
        try {
            def u = 'https://maps.googleapis.com/maps/api/geocode/json?address='+parametrosUbicacion+'&components=locality:'+localidad+'|country:'+pais+'&key=AIzaSyBPoinlpS-PDoL1LkvfbTmMLxBcFyrb1YM'
            println(u)
            lista = this.obtenerJson(u)
            println()
        } catch (Exception e) {
            return null
        }
        def lat = lista.results.geometry.location.lat[0]
        def lng = lista.results.geometry.location.lng[0]
        return([lat,lng])
    }

    def getLocations(lat,lng,metodoPago,radio,cantidadSitios){
        def linkSites = 'https://api.mercadolibre.com/sites/MLA/payment_methods/'+metodoPago+'/agencies?near_to='+lat+','+lng+','+radio+'&limit='+cantidadSitios

        def lista2
        try {
            lista2 = this.obtenerJson(linkSites)
        } catch (Exception e) {
            return null
        }
        //obtengo del json los datos que me interesan
        def descripciones = lista2.results.description
        def distancia = lista2.results.distance
        def d1 = lista2.results.address.address_line
        def d2 = lista2.results.address.city
        def d3 = lista2.results.address.country
        def location = lista2.results.address.location
        def otherInfo = lista2.results.address.other_info
        def state = lista2.results.address.state
        def zipCode = lista2.results.address.zip_code
        println(zipCode)
        def comb = [descripciones, distancia,d1,d2,d3,location,otherInfo, state, zipCode].transpose()

        def locs = []
        for(def i = 0;i<descripciones.size();i++){
            if(location != []) {
                locs.add([descripciones[i], Double.parseDouble(location[i].split(",")[0]), Double.parseDouble(location[i].split(",")[1]), i])
            }
        }

        //necesario para pasar los datos a js
        def locations = locs as JSON

        return([locations,comb])
    }



}
