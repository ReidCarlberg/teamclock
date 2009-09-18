<%@ taglib uri="webwork" prefix="webwork" %>

<%@ taglib uri="fstxtime" prefix="time" %>

<!--header-->
<div align="center">
<table width="650" border="1" style="border-collapse: collapse;"><tr><td align="left">
<table width="600"> <tr><td align="center">
<h1 style="align: center; font-family: arial, helvetica;"><webwork:property value="ebayItemModifyContext.target.listingHeadline"/></h1>
<p style="align: center; font-family: arial, helvetica;">
<webwork:iterator value="ebayItemModifyContext.images">
<img border="0" 
src="<webwork:property value="sessionContext.settings.ebayImagesSrc" />/<webwork:property value="imageSmall" />">
</webwork:iterator>
<br>See below for full size images.  
<br>This auction presented by <a href="http://search.ebay.com/_W0QQgotopageZ1QQsassZfivesticksQQsorecordsperpageZ25QQsosortorderZ1QQsosortpropertyZ1">NorthAvenueAuction.com</a>.</center>

</td>
</tr>
<tr>
<td align="left">

<!--description-->
<p style="font-family: arial, helvetica;" ><webwork:property value="ebayItemModifyContext.formattedListing"/>



<!--images-->

<webwork:iterator value="ebayItemModifyContext.images">
<p><img border="0" 
src="<webwork:property value="sessionContext.settings.ebayImagesSrc" />/<webwork:property value="imageFull" />">
</webwork:iterator>

<!--footer-->
<p style="align: center; font-family: arial, helvetica;">Questions?  Please ask prior to bidding.  <a href="http://search.ebay.com/_W0QQgotopageZ1QQsassZfivesticksQQsorecordsperpageZ25QQsosortorderZ1QQsosortpropertyZ1">See our other auctions.</a></p>

<p style="align: center; font-family: arial, helvetica; font-size: 12px;"><strong>Auction Details</strong>

<br><strong>When You Win:</strong> Please contact us within 24 hours of auction close.  Let us know 
how you plan to pay for item and when.

<br><strong>Payment:</strong>  We accept PayPal, Money Orders and Personal Checks.  PayPal is the fastest way to complete
payment.  Personal checks will be held for seven (7) days to ensure the funds clear.  When you win, please
plan on paying for your item within three (3) business days.  Sending a Money Order or Check?  
Simply let us know.  Picking your item up?  Please bring cash or money order, exact change only.

<br><strong>Shipping:</strong> Please use the shipping calculator to compute shipping costs prior to bidding.  
Items are
shipped to the winning bidder within two business days of payment.
We ship ONLY via UPS.  We cannot ship to PO boxes, APOs or FPOs.  
We do not offer shipping discounts for combined purchases at this time.
Prefer to pick your item up?  Our address is 6957 W North Ave., Oak Park, IL 60302.  

<br><strong>International Customers:</strong>  We appreciate your interest!  We are currently expanding our international operations to serve you better.
At this time, we ship ONLY TO THE FOLLOWING AREAS: Canada, Western Europe and Japan.  Note that you will be responsible
for any duties or taxes your country may require.

<!--We can ship overseas using ONLY UPS.  
We cannot 
use the US Postal Service, which means we CANNOT ship via Airmail or International Parcel Post.  Serious bidders
are welcome to contact us for a custom quote.-->

<br><strong>Warranty and Returns:</strong>  Items are
sold as is and without warranty of any kind, including that of merchantability or fitness for a 
particular purpose.  Returns are accepted IF AND ONLY IF the item you purchase is not as described in the above
listing.  You must contact us prior to returning any item, and you must contact us within three business days 
of receiving the item.  

<br><strong>About Us:</strong> North Avenue Auction is an eBay drop off service.  
Customers drop off items and we handle the rest. 
We are not the owner of the items we sell.  Our hours are Tuesday through Saturday, 10a-6p.  See our website at NorthAvenueAuction.com.


</p>


</td></tr></table>
</div>
