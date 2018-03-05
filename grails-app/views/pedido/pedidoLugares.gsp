

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <asset:stylesheet src="bootstrap.css"/>
    <asset:javascript src="jquery-2.2.0.min.js"/>
    <asset:javascript src="bootstrap.js"/>
    <style>
    #map {
        height: 400px;
        width: 100%;
    }
    </style>
    <title></title>
</head>
<body>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js">	</script>
<g:javascript>
    $(document).ready(function(){
    });

    var generarDetalles = function(d1,d2,d3,d4,d5,state,zipCode){
        var htmlCode = "";
        if(d5 === ""){
            htmlCode = 'Direccion: '+d1+'<br>'+'Ciudad: '+d2+'<br>'+'Pais: '+d3+'<br>'+'Locacion: '+d4+'<br>'+state+'<br>'+zipCode+'<br>';
        } else {
            htmlCode = 'Direccion: '+d1+'<br>'+'Ciudad: '+d2+'<br>'+'Pais: '+d3+'<br>'+'Locacion: '+d4+'<br>'+'Otra info: '+d5+'<br>'+state+'<br>'+zipCode+'<br>';
        }
        $('#rellenoModal').html(htmlCode);
    }

</g:javascript>

<g:javascript>
    function initMap() {
        var lugares = "${locations}";
        var lugaresBienFormado = lugares.replace(/&quot;/g, '"');
        var locations = JSON.parse(lugaresBienFormado);
        console.log(locations)

        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 15,
            center: new google.maps.LatLng("${centerLat}", "${centerLng}"),
            mapTypeId: google.maps.MapTypeId.ROADMAP
        });

        var infowindow = new google.maps.InfoWindow();

        var marker, i;

        for (i = 0; i < locations.length; i++) {
            marker = new google.maps.Marker({
                position: new google.maps.LatLng(locations[i][1], locations[i][2]),
                map: map
            });

            google.maps.event.addListener(marker, 'click', (function(marker, i) {
                return function() {
                    infowindow.setContent(locations[i][0]);
                    infowindow.open(map, marker);
                }
            })(marker, i));
        }
    }
</g:javascript>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBPoinlpS-PDoL1LkvfbTmMLxBcFyrb1YM
&callback=initMap">
</script>

    <div id="tablita" style="width:50%; margin:0 auto;">
        <table class="table">
            <thead>
                <tr>
                    <th>
                        Descripción
                    </th>
                    <th>
                        Distancia
                    </th>
                    <th>
                        Detalles
                    </th>
                </tr>
            </thead>
            <tbody>
            <g:each in="${list}">
                <tr>
                    <th>
                        ${it[0]}
                    </th>
                    <th>
                        ${it[1]}
                    </th>
                    <th>
                        <button type="button" data-toggle="modal" data-target="#myModal" class="btn btn-info" onclick="generarDetalles('${it[2]}','${it[3]}','${it[4]}','${it[5]}','${it[6]}','${it[7]}','${it[8]}')">
                            <img src="https://cdn2.iconfinder.com/data/icons/circle-icons-1/64/magnifyingglass-128.png" alt="lupa" width="25px" height="25px">
                        </button>
                    </th>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>


    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Detalles del sitio de pago</h4>
                </div>
                <div class="modal-body" id="rellenoModal">
                    esto es mio
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <div align="center">
        <h3>Ubicaciones:</h3>
        <div id="map" style="width: 50%;"></div>
    </div>
</body>
</html>