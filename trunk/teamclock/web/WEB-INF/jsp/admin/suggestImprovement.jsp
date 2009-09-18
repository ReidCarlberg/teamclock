<%@ taglib uri="webwork" prefix="webwork" %>
 

<html>
<head><title>Suggest Improvement</title></head>
<body>



<webwork:if test="sessionContext.messagePresent">
	<h2><webwork:property value="sessionContext.message" /></h2>
</webwork:if>

<h2>Suggest Improvement</h2>

<p>Please tell us how we can make FSTX Time better for you.



<webwork:form action="'suggestImprovement.action'" method="'post'">

<webwork:textarea label="'Suggestion'" name="'suggestion'" value="" cols="'40'" rows="'10'" />



<webwork:submit name="'submitSuggestion'" value="'Send Suggestion'" />


</webwork:form>


</body>
</html>