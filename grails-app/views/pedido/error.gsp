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
        <div id="metodoPago">
        </div>

        <div class="h-100 row align-items-center" align="center">
            <div class="form-group">
                <form action="/pedido/index" method="get">
                    <h1>${mensaje}</h1>
                    <input type="submit" id="volver" value="Volver al inicio" class="form-control" style="width:50%" align="center"><br>
                </form>
            </div>
        </div>
    </section>
</div>

</body>
</html>
