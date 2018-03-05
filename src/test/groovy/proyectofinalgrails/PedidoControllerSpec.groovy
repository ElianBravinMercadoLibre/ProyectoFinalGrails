package proyectofinalgrails

import grails.testing.web.controllers.ControllerUnitTest
import groovy.json.JsonSlurper
import org.spockframework.compiler.model.Spec
import org.springframework.http.HttpStatus
import spock.lang.Specification

import java.awt.List

class PedidoControllerSpec extends Specification implements ControllerUnitTest<PedidoController> {

    def setup() {
    }

    def cleanup() {
    }

    void 'test index redireccion error'() {
        given:
        PedidoService pedidoService = Mock()
        pedidoService.getTicketList() >> null
        controller.pedidoService = pedidoService

        when:
        def model = controller.index()

        then:
        response.redirectedUrl == '/pedido/error'
    }

    void 'test index tipo de datos del modelo ok'() {
        given:
        PedidoService pedidoService = Mock()
        pedidoService.getTicketList() >> new ArrayList()
        controller.pedidoService = pedidoService


        when:
        def model = controller.index()

        then:
        model.listaTickets instanceof ArrayList
    }

    void 'test index redireccion ok'() {
        given:
        PedidoService pedidoService = Mock()
        pedidoService.getTicketList() >> new ArrayList()
        controller.pedidoService = pedidoService

        when:
        def model = controller.index()

        then:
        response.status == 200
    }

    void 'test error ok'(){
        given:
        PedidoService pedidoService = Mock()
        controller.pedidoService = pedidoService

        when:
        def model = controller.error()

        then:
        response.status == 200
    }

    void 'test error tipo de datos del modelo ok'() {
        given:
        PedidoService pedidoService = Mock()
        controller.pedidoService = pedidoService


        when:
        def model = controller.error()

        then:
        model.mensaje instanceof String
    }




    void 'test pedidoLugares redireccion error'() {
        given:
        PedidoService pedidoService = Mock()
        pedidoService.getTicketList() >> null
        controller.pedidoService = pedidoService

        when:
        def model = controller.index()

        then:
        response.redirectedUrl == '/pedido/error'
    }

    void 'test pedidoLugares tipo de datos del modelo ok'() {
        given:
        PedidoService pedidoService = Mock()
        pedidoService.getLatLon(_,_,_) >> [new BigDecimal(2),new BigDecimal(2)]
        pedidoService.getLocations(_,_,_,_,_) >> [new ArrayList(),new ArrayList()]
        controller.pedidoService = pedidoService


        when:
        def model = controller.pedidoLugares()

        then:
        model.list instanceof ArrayList
        model.locations instanceof ArrayList
        model.centerLng instanceof BigDecimal
        model.centerLat instanceof BigDecimal
    }

    void 'test pedidoLugares redireccion ok'() {
        given:
        PedidoService pedidoService = Mock()
        pedidoService.getLatLon(_,_,_) >> [new BigDecimal(2),new BigDecimal(2)]
        pedidoService.getLocations(_,_,_,_,_) >> [new ArrayList(),new ArrayList()]
        controller.pedidoService = pedidoService


        when:
        def model = controller.pedidoLugares()

        then:
        response.status == 200
    }

    void 'test pedidoLugares redireccion a error por getLatLon'() {
        given:
        PedidoService pedidoService = Mock()
        pedidoService.getLatLon(_,_,_) >> null
        pedidoService.getLocations(_,_,_,_,_) >> [new ArrayList(),new ArrayList()]
        controller.pedidoService = pedidoService


        when:
        def model = controller.pedidoLugares()

        then:
        response.redirectedUrl == '/pedido/error'
    }

    void 'test pedidoLugares redireccion a error por getLocations'() {
        given:
        PedidoService pedidoService = Mock()
        pedidoService.getLatLon(_,_,_) >> [new BigDecimal(2),new BigDecimal(2)]
        pedidoService.getLocations(_,_,_,_,_) >> null
        controller.pedidoService = pedidoService


        when:
        def model = controller.pedidoLugares()

        then:
        response.redirectedUrl == '/pedido/error'
    }

}
