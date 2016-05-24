
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${msgErro != null}">
	<div class="alert alert-danger">
		<c:out value="${msgErro}"></c:out>
	</div> 
</c:if>