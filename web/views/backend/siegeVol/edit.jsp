<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.vol.SiegeVol" %>
<%@ page import="models.Siege" %>
<%@ page import="java.util.List" %>

<jsp:include page="../../fragments/header.jsp" />

<%
    SiegeVol siegeVol = (SiegeVol) request.getAttribute("siegeVol");
    List<Siege> sieges = (List<Siege>) request.getAttribute("sieges");
%>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <div class="card">
                <div class="card-header">
                    <h3 class="card-title">Modifier un siège de vol</h3>
                </div>
                <div class="card-body">
                    <form action="/TripZip/vols/sieges/edit" method="POST">
                        <input type="hidden" name="idSiegeVol" value="<%= siegeVol.getIdSiegeVol() %>">
                        <input type="hidden" name="idVol" value="<%= siegeVol.getIdVol() %>">
                        
                        <div class="form-group">
                            <label for="idSiege">Type de siège</label>
                            <select class="form-control" id="idSiege" name="idSiege" required>
                                <% for(Siege siege : sieges) { %>
                                    <option value="<%= siege.getId() %>" 
                                            <%= siege.getId() == siegeVol.getIdSiege() ? "selected" : "" %>>
                                        <%= siege.getNom() %>
                                    </option>
                                <% } %>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="montant">Prix normal</label>
                            <input type="number" class="form-control" id="montant" name="montant" 
                                   value="<%= siegeVol.getMontant() %>" required step="0.01">
                        </div>

                        <div class="form-group">
                            <label for="prom">Prix promotionnel</label>
                            <input type="number" class="form-control" id="prom" name="prom" 
                                   value="<%= siegeVol.getProm() %>" required step="0.01">
                        </div>

                        <div class="form-group">
                            <label for="siegeProm">Nombre de sièges en promotion</label>
                            <input type="number" class="form-control" id="siegeProm" name="siegeProm" 
                                   value="<%= siegeVol.getSiegeProm() %>" required min="0">
                        </div>

                        <button type="submit" class="btn btn-primary">Enregistrer</button>
                        <a href="/TripZip/vols/detail?idVol=<%= siegeVol.getIdVol() %>" 
                           class="btn btn-secondary">Annuler</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../../fragments/footer.jsp" />