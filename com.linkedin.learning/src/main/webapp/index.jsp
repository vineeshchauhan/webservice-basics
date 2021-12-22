<html>
<head>
	<script>
		var sseListener = new EventSource("http://localhost:8080/com.linkedin.learning/webapi/sse/guest/Vineesh/salute");
		sseListener.onmessage = function(event){
			var outputNode = document.getElementById('sseOutPut');
			outputNode.appendChild(document.createElement('p'));
			outputNode.appendChild(document.createTextNode(event.data));
			outputNode.appendChild(document.createElement('br'));
		};
	</script>
</head>
<body>
    <h2>Jersey RESTful Web Application!</h2>
    <p><a href="webapi/myresource">Jersey resource</a>
    <p>Visit <a href="http://jersey.java.net">Project Jersey website</a>
    for more information on Jersey!
    <p>
    <form action="webapi/myresource/guest/salute" enctype="application/x-www-form-urlencoded" method="post">
    <p>
    Salutation : <input type="text" name="salutation"/>
    <p>
    <button type="submit">Say Hello to:</button>
    <input type="text" name="guest"/>
    </form>
    <p><div id="sseOutPut"/>
</body>
</html>
