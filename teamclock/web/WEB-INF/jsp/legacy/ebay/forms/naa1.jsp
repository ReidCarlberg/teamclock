<%@ taglib uri="webwork" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>



<html>
<head>
<title>Listing</title>
</head>
<body>
<p>Cut and paste this listing below.
<form>

<textarea cols="70" rows="30">
<jsp:include page="_naa.jsp" flush="true" />
</textarea>

<h1>Preview</h1>

<jsp:include page="_naa.jsp" flush="true" />

</form>

</body>