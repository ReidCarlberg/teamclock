<%@ taglib uri="webwork" prefix="webwork" %>


<html>
	<head>
		<title>Project Modify</title>
	</head>
	<body>
		<h2>Project Modify</h2>
		
		<webwork:form action="'customerProjectModify.action'" method="'post'">

		<webwork:textfield label="'Long Name'" name="'targetProject.longName'" value="targetProject.longName" />

		<webwork:textfield label="'Short Name'" name="'targetProject.shortName'" value="targetProject.shortName" />
		 
		<webwork:checkbox label="'Active?'" name="'targetProject.active'" value="targetProject.active" fieldValue="'true'" />		

		<webwork:checkbox label="'Postable?'" name="'targetProject.postable'" value="targetProject.postable" fieldValue="'true'" />

		<webwork:select label="'Class'" name="'targetProject.projectClassId'" value="targetProject.projectClassId"
			list="projectClassTypes" listKey="id" listValue="fullName" 
			emptyOption="true"   />
							
		<webwork:submit value="'Save'" name="'saveProject'" />
		
		<webwork:if test="projectModifyContext.targetProject != null">
				<webwork:submit value="'Delete'" name="'submitDeleteProject'" />
		</webwork:if>

				
		<webwork:submit value="'Cancel'" name="'cancelProject'" />

		</webwork:form>
		
	</body>
</html>

