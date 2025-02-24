<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Mise à jour du Vol</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
</head>
<body class="min-h-screen bg-background font-sans antialiased">
    <!-- Include header fragment -->
    <jsp:include page="/TripZip/views/fragments/header.jsp" />

    <!-- Formulaire de mise à jour de Vol -->
    <main class="container px-4 py-8">
        <div class="mb-8 space-y-1">
            <h1 class="text-3xl font-semibold tracking-tight">Mise à jour du Vol</h1>
            <p class="text-muted-foreground">Modifiez les informations du vol</p>
        </div>
        <%
            models.vol.Vol vol = (models.vol.Vol) request.getAttribute("vol");
            String dateVolVal = (vol.getDateVol() != null) ? vol.getDateVol().toString().replace(" ", "T") : "";
            String reservationVal = (vol.getReservation() != null) ? vol.getReservation().toString().replace(" ", "T") : "";
            String annulationVal = (vol.getAnnulation() != null) ? vol.getAnnulation().toString().replace(" ", "T") : "";
        %>
        <form action="/TripZip/vols" method="POST" id="volForm" class="space-y-6">
            <div>
                <label for="dateVol" class="block text-sm font-medium text-foreground">Date du Vol</label>
                <input type="datetime-local" name="dateVol" id="dateVol" required value="<%= dateVolVal %>"
                 class="mt-1 block w-full rounded-md border border-gray-300 p-2" />
            </div>
            <div>
                <label for="villeDepart" class="block text-sm font-medium text-foreground">Ville de Départ</label>
                <select name="villeDepart" id="villeDepart" required class="mt-1 block w-full rounded-md border border-gray-300 p-2">
                    <!-- ...existing code for villes loop... -->
                    <% 
                        java.util.List<models.vol.Ville> villes = (java.util.List<models.vol.Ville>) request.getAttribute("villes");
                        for(models.vol.Ville v : villes) { 
                    %>
                        <option value="<%= v.getIdVille() %>" <%= (v.getIdVille() == vol.getIdVilleDepart()) ? "selected" : "" %>><%= v.getNom() %></option>
                    <% } %>
                </select>
            </div>
            <div>
                <label for="villeArrivee" class="block text-sm font-medium text-foreground">Ville d'Arrivée</label>
                <select name="villeArrivee" id="villeArrivee" required class="mt-1 block w-full rounded-md border border-gray-300 p-2">
                    <!-- ...existing code for villes loop... -->
                    <% 
                        for(models.vol.Ville v : villes) { 
                    %>
                        <option value="<%= v.getIdVille() %>" <%= (v.getIdVille() == vol.getIdVilleArrivee()) ? "selected" : "" )%>><%= v.getNom() %></option>
                    <% } %>
                </select>
            </div>
            <div>
                <label for="idAvion" class="block text-sm font-medium text-foreground">Avion</label>
                <select name="idAvion" id="idAvion" required class="mt-1 block w-full rounded-md border border-gray-300 p-2">
                    <!-- ...existing code for avions loop... -->
                    <% 
                        java.util.List<models.avion.Avion> avions = (java.util.List<models.avion.Avion>) request.getAttribute("avions");
                        for(models.avion.Avion a : avions) { 
                    %>
                        <option value="<%= a.getIdAvion() %>" <%= (a.getIdAvion() == vol.getIdAvion()) ? "selected" : "" %>><%= a.getModele() %></option>
                    <% } %>
                </select>
            </div>
            <div>
                <label for="reservation" class="block text-sm font-medium text-foreground">Date de Reservation</label>
                <input type="datetime-local" name="reservation" id="reservation" required value="<%= reservationVal %>"
                 class="mt-1 block w-full rounded-md border border-gray-300 p-2" />
            </div>
            <div>
                <label for="annulation" class="block text-sm font-medium text-foreground">Date d'Annulation</label>
                <input type="datetime-local" name="annulation" id="annulation" required value="<%= annulationVal %>"
                 class="mt-1 block w-full rounded-md border border-gray-300 p-2" />
            </div>

            <button type="submit" class="mt-4 w-full rounded-md bg-primary py-2 text-sm font-semibold text-white hover:bg-primary-700">
                Mettre à jour le vol
            </button>
        </form>
    </main>

    <script>
        // Valider que villeDepart et villeArrivee sont différentes
        document.getElementById("volForm").addEventListener("submit", function(e) {
            var depart = document.getElementById("villeDepart").value;
            var arrivee = document.getElementById("villeArrivee").value;
            if(depart === arrivee) {
                e.preventDefault();
                alert("La ville d'arrivée doit être différente de la ville de départ.");
                return;
            }
        });
    </script>
</body>
</html>
