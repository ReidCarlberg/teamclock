<%@ taglib uri="webwork" prefix="webwork" %>

<webwork:if test="ebayItemListContext.viewType.name == 'AGREEMENT'"> 
	<jsp:include page="_auctionItemsAgree.jsp" flush="true" />
</webwork:if>
		
<webwork:elseif test="ebayItemListContext.viewType.name == 'STATUS_UPDATE'"> 
	<jsp:include page="_auctionItemsStatusUpdate.jsp" flush="true" />
</webwork:elseif>

<webwork:else>
	<jsp:include page="_auctionItemsStandard.jsp" flush="true" />
</webwork:else>		

<webwork:if test="ebayItemListContext.viewType == null">
	<jsp:include page="_auctionItemsStandard.jsp" flush="true" />
</webwork:if> 
