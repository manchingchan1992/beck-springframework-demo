<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Welcome To Web OA System</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
					<div class="boxTitleBar">
							<div class="contenttitle">Print List Of CCRN</div>
						</div>
						<div style="display: none;">
							<input type="text" name="actionFlag" id="actionFlag">
						</div>
						<table class="contentTableBody1" cellspacing="0">
							<tr class="contentTableRow1">
								<td class="contentLableTd"></td>
								<td class="contentLableTd"></td>
								<td class="contentInputTd"></td>
							</tr>
							<tr class="contentTableRow1">
								<td class="contentLableTd">CCRN</td>
								<td class="contentInputTd"><input type="text"
									class="standardInputText" />
								</td>
								<td class="contentLableTd">Consignment Status</td>
								<td class="contentInputTd"><input type="text"
									class="standardInputText" />
								</td>
							</tr>
							<tr class="contentTableRow1">
								<td class="contentLableTd">Consignee Name</td>
								<td class="contentInputTd"><input type="text"
									class="standardInputText" />
								</td>
								<td class="contentLableTd">Consignor Name</td>
								<td class="contentInputTd"><input type="text"
									class="standardInputText" />
								</td>
							</tr>
							<tr class="contentTableRow1">
								<td class="contentLableTd">Import/Export</td>
								<td class="contentInputTd"><input type="text"
									class="standardInputText" />
								</td>
								<td class="contentLableTd">Submission Type</td>
								<td class="contentInputTd"><input type="text"
									class="standardInputText" />
								</td>
							</tr>
							<tr class="contentTableRow1">
								<td class="contentLableTd">Message Sender's Reference</td>
								<td class="contentInputTd"><input type="text"
									class="standardInputText" />
								</td>
								<td class="contentLableTd">Submitted By</td>
								<td class="contentInputTd"><input type="text"
									class="standardInputText" />
								</td>
							</tr>
						</table>
						<div class="emptyBlock"></div> <input class="submit" type="submit"
						value="Submit" id="submitButton" style="display: none" />
						<div id="buttonArea">
							<div class="buttonmenubox_R">
								<a class="buttondefault" onclick="validateAndSubmitForm('1')"
									href="#">Search</a>
							</div>
						</div>
</body>
</html>