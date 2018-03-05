<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <asset:stylesheet src="bootstrap.css"/>
    <asset:javascript src="jquery-2.2.0.min.js"/>
    <asset:javascript src="bootstrap.js"/>
    <title>Localizacion de medios de pago</title>
</head>
<body>
    <div id="content" role="main">
        <section class="row colset-2-its">
            <br>
            <br>
            <br>
            <br>

            <div class="h-100 row align-items-center" align="center">
                    <div class="form-group">
                        <form action="/pedido/pedidoLugares" method="get">

                            <g:select name="medioPago"
                                      id="medioPago"
                                      from="${listaTickets}"
                                      optionKey="id"
                                      optionValue="name"
                            /><br>

                            Direccion:
                            <input type="text" id="direccion" name="direccion" class="form-control" style="width:50%" align="center" required><br>
                            Localidad:
                            <input type="text" id="localidad" name="localidad" class="form-control" style="width:50%" align="center"><br>
                            Pais:
                            <input type="text" id="pais" name="pais" class="form-control" style="width:50%" align="center"><br>
                            Max cantidadd resultados:
                            <input type="number" min="1" max="100" id="cant" name="cant" value="10" class="form-control" style="width:50%" align="center" required><br>
                            Radio en mts:
                            <input type="number" min="1" id="radio" name="radio" class="form-control" style="width:50%" align="center" required><br>
                            <input type="submit" id="enviar" value="enviar" class="form-control" style="width:50%" align="center"><br>
                        </form>
                </div>
            </div>
        </section>
    </div>

</body>
</html>
