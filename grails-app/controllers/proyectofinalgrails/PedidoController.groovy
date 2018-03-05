package proyectofinalgrails

import constantes.Constantes
import grails.converters.JSON
import groovy.json.JsonSlurper

import java.util.concurrent.ExecutionException

class PedidoController {
    PedidoService pedidoService
    def index() {
        def listaTickets = pedidoService.getTicketList()
        if(listaTickets == null){
            redirect(controller: "pedido", action: "error")
        }
        [listaTickets: listaTickets]
    }

    def error(){
        [mensaje:Constantes.errorMsg]
    }

    def pedidoLugares(){
        //obtencion de parametros, chequeo de datos recibidos y formacion del pedido a la api de ml
        def metodoPago = params.get("medioPago")
        def direccion = params.get("direccion")
        def localidad = params.get("localidad")
        def pais = params.get("pais")
        def cantidadSitios = params.get("cant") ////cambiar y poner en constantes
        def radio = params.get("radio")
        def arr = pedidoService.getLatLon(direccion,localidad,pais)
        def lat
        def lng
        if(arr == null){
            redirect(controller:"pedido", action: "error")
            return
        } else {
            lat = arr[0]
            lng = arr[1]
        }
        //obtengo los datos acerca de los lugares de pago seleccionados
        def arr2 = pedidoService.getLocations(lat,lng,metodoPago,radio,cantidadSitios)
        def locations
        def comb
        if(arr2 == null){
            redirect(controller:"pedido", action: "error")
            return
        } else {
            locations = arr2[0]
            comb = arr2[1]
        }
        [list:comb, locations:locations, centerLat:lat, centerLng:lng]
    }
}
