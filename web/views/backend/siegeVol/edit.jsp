<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="models.vol.SiegeVol" %>
<%@ page import="models.Siege" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>Détails du Vol</title>
	<link rel="stylesheet" href="/TripZip/assets/style.min.css">
	<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet" />
	<script>
		tailwind.config = {
			theme: {
				extend: {
					colors: {
						border: "hsl(214.3 31.8% 91.4%)",
						input: "hsl(214.3 31.8% 91.4%)",
						ring: "hsl(215 20.2% 65.1%)",
						background: "hsl(0 0% 100%)",
						foreground: "hsl(222.2 47.4% 11.2%)",
						primary: {
							DEFAULT: "hsl(222.2 47.4% 11.2%)",
							foreground: "hsl(210 40% 98%)",
						},
						secondary: {
							DEFAULT: "hsl(210 40% 96.1%)",
							foreground: "hsl(222.2 47.4% 11.2%)",
						},
						muted: {
							DEFAULT: "hsl(210 40% 96.1%)",
							foreground: "hsl(215.4 16.3% 46.9%)",
						},
						accent: {
							DEFAULT: "hsl(210 40% 96.1%)",
							foreground: "hsl(222.2 47.4% 11.2%)",
						},						
					},
				},
			},
		}
	</script>
</head>
<body class="min-h-screen bg-background font-sans antialiased">
	<jsp:include page="/views/fragments/header.jsp" />

<%
    SiegeVol siegeVol = (SiegeVol) request.getAttribute("siegeVol");
    List<Siege> sieges = (List<Siege>) request.getAttribute("sieges");
%>

<div class="container mx-auto px-4 py-8">
    <div class="max-w-3xl mx-auto">
        <div class="bg-white rounded-lg shadow-md">
            <div class="border-b border-gray-200 p-4">
                <h3 class="text-lg font-semibold text-gray-900">Modifier un siège de vol</h3>
            </div>
            <div class="p-6">
                <form action="/TripZip/vols/sieges/edit" method="POST">
                    <input type="hidden" name="idSiegeVol" value="<%=siegeVol.getIdSiegeVol()%>">
                    <input type="hidden" name="idVol" value="<%=siegeVol.getIdVol()%>">
                    
                    <div class="mb-6">
                        <label for="idSiege" class="block text-sm font-medium text-gray-700 mb-2">Type de siège</label>
                        <select class="w-full rounded-md border border-input bg-background px-3 py-2" id="idSiege" name="idSiege" required>
                            <% for(Siege siege : sieges) { %>
                                <option value="<%= siege.getId() %>" 
                                        <%= siege.getId() == siegeVol.getIdSiege() ? "selected" : "" %>>
                                    <%= siege.getNom() %>
                                </option>
                            <% } %>
                        </select>
                    </div>

                    <div class="mb-6">
                        <label for="montant" class="block text-sm font-medium text-gray-700 mb-2">Prix normal</label>
                        <input type="number" class="w-full rounded-md border border-input bg-background px-3 py-2" 
                               id="montant" name="montant" value="<%= siegeVol.getMontant() %>" required step="0.01">
                    </div>

                    <div class="mb-6">
                        <label for="prom" class="block text-sm font-medium text-gray-700 mb-2">Prix promotionnel</label>
                        <input type="number" class="w-full rounded-md border border-input bg-background px-3 py-2" 
                               id="prom" name="prom" value="<%= siegeVol.getProm() %>" required step="0.01">
                    </div>

                    <div class="mb-6">
                        <label for="siegeProm" class="block text-sm font-medium text-gray-700 mb-2">Nombre de sièges en promotion</label>
                        <input type="number" class="w-full rounded-md border border-input bg-background px-3 py-2" 
                               id="siegeProm" name="siegeProm" value="<%= siegeVol.getSiegeProm() %>" required min="0">
                    </div>

                    <div class="flex gap-4">
                        <button type="submit" class="px-4 py-2 bg-primary text-primary-foreground rounded-md hover:bg-primary/90">
                            Enregistrer
                        </button>
                        <a href="/TripZip/vols/detail?idVol=<%= siegeVol.getIdVol() %>" 
                           class="px-4 py-2 bg-secondary text-secondary-foreground rounded-md hover:bg-secondary/90">
                            Annuler
                        </a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>