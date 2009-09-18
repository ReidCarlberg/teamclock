<%@ taglib uri="webwork" prefix="webwork" %>


<webwork:if test="stats != null">
	<table width="100%">
		<tr>
			<td width="25%">Items: <webwork:property value="stats.count" /></td>
			<td width="25%">Sale Price: <webwork:text name="'format.money'" ><webwork:param value="stats.sale" /></webwork:text> </td>		
			<td width="25%">Commission:  <webwork:text name="'format.money'" ><webwork:param value="stats.commission" /></webwork:text> </td>
			<td width="25%">Net: <webwork:text name="'format.money'"  ><webwork:param value="stats.net" /></webwork:text></td>		
		</tr>
	</table>
</webwork:if>


