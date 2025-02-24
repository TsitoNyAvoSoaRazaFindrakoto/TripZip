<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Mise a jour du Vol</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap');
        .font-sans { font-family: 'Inter', sans-serif; }
    </style>
</head>
<body class="min-h-screen bg-background font-sans antialiased">
    <!-- Include header fragment -->
    <jsp:include page="/views/fragments/header.jsp" />

    <!-- Formulaire de mise a jour de Vol -->
    <main class="container px-4 py-8">
        <div class="mb-8 space-y-1">
            <h1 class="text-3xl font-semibold tracking-tight">Mise a jour du Vol</h1>
            <p class="text-sm text-muted-foreground">Modifiez les informations du vol</p>
        </div>

        <%
            models.vol.Vol vol = (models.vol.Vol) request.getAttribute("vol");
            String dateVolVal = (vol.getDateVol() != null) ? vol.getDateVol().toString().replace(" ", "T") : "";
            String reservationVal = (vol.getReservation() != null) ? vol.getReservation().toString().replace(" ", "T") : "";
            String annulationVal = (vol.getAnnulation() != null) ? vol.getAnnulation().toString().replace(" ", "T") : "";
        %>

        <form action="/TripZip/vols" method="POST" id="volForm" class="space-y-6">
            <!-- Date du Vol -->
            <div class="space-y-2">
                <label class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70">
                    Date du Vol
                </label>
                <input 
                    type="datetime-local" 
                    name="dateVol" 
                    id="dateVol" 
                    required 
                    value="<%= dateVolVal %>"
                    class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2"
                />
            </div>

            <!-- Villes -->
            <div class="grid gap-4 md:grid-cols-2">
                <div class="space-y-2">
                    <label class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70">
                        Ville de Depart
                    </label>
                    <select 
                        name="villeDepart" 
                        id="villeDepart" 
                        required
                        class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 appearance-none"
                    >
                        <% 
                            java.util.List<models.vol.Ville> villes = (java.util.List<models.vol.Ville>) request.getAttribute("villes");
                            for(models.vol.Ville v : villes) { 
                        %>
                            <option value="<%= v.getIdVille() %>" <%= (v.getIdVille() == vol.getIdVilleDepart()) ? "selected" : "" %>><%= v.getNom() %></option>
                        <% } %>
                    </select>
                </div>

                <div class="space-y-2">
                    <label class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70">
                        Ville d'Arrivee
                    </label>
                    <select 
                        name="villeArrivee" 
                        id="villeArrivee" 
                        required
                        class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 appearance-none"
                    >
                        <% 
                            for(models.vol.Ville v : villes) { 
                        %>
                            <option value="<%= v.getIdVille() %>" <%= (v.getIdVille() == vol.getIdVilleArrivee()) ? "selected" : "" %>><%= v.getNom() %></option>
                        <% } %>
                    </select>
                </div>
            </div>

            <!-- Avion -->
            <div class="space-y-2">
                <label class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70">
                    Avion
                </label>
                <select 
                    name="idAvion" 
                    id="idAvion" 
                    required
                    class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 appearance-none"
                >
                    <% 
                        java.util.List<models.avion.Avion> avions = (java.util.List<models.avion.Avion>) request.getAttribute("avions");
                        for(models.avion.Avion a : avions) { 
                    %>
                        <option value="<%= a.getIdAvion() %>" <%= (a.getIdAvion() == vol.getIdAvion()) ? "selected" : "" %>><%= a.getModele() %></option>
                    <% } %>
                </select>
            </div>

            <!-- Dates de Reservation et Annulation -->
            <div class="grid gap-4 md:grid-cols-2">
                <div class="space-y-2">
                    <label class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70">
                        Date de Reservation
                    </label>
                    <input 
                        type="datetime-local" 
                        name="reservation" 
                        id="reservation" 
                        required 
                        value="<%= reservationVal %>"
                        class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2"
                    />
                </div>

                <div class="space-y-2">
                    <label class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70">
                        Date d'Annulation
                    </label>
                    <input 
                        type="datetime-local" 
                        name="annulation" 
                        id="annulation" 
                        required 
                        value="<%= annulationVal %>"
                        class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2"
                    />
                </div>
            </div>

            <!-- Bouton de soumission -->
            <button 
                type="submit" 
                class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground hover:bg-primary/90 h-10 px-4 py-2 w-full"
            >
                Mettre a jour le vol
            </button>
        </form>
    </main>

    <script>
        // Valider que villeDepart et villeArrivee sont differentes
        document.getElementById("volForm").addEventListener("submit", function(e) {
            const depart = this.villeDepart.value;
            const arrivee = this.villeArrivee.value;
            
            if(depart === arrivee) {
                e.preventDefault();
                alert("La ville d'arrivee doit être differente de la ville de depart.");
                return;
            }
        });

        // Ajout des icônes de chevron pour les selects
        document.querySelectorAll('select').forEach(select => {
            const wrapper = document.createElement('div');
            wrapper.className = 'relative';
            
            const icon = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
            icon.setAttribute('class', 'h-4 w-4 absolute right-3 top-1/2 -translate-y-1/2 pointer-events-none');
            icon.setAttribute('viewBox', '0 0 24 24');
            icon.setAttribute('fill', 'none');
            icon.setAttribute('stroke', 'currentColor');
            icon.setAttribute('stroke-width', '2');
            icon.setAttribute('stroke-linecap', 'round');
            icon.setAttribute('stroke-linejoin', 'round');
            icon.innerHTML = '<path d="m6 9 6 6 6-6"/>';
            
            select.parentNode.insertBefore(wrapper, select);
            wrapper.appendChild(select);
            wrapper.appendChild(icon);
        });
    </script>
</body>
</html>