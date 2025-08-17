<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Creation de Vol</title>
    	<link rel="stylesheet" href="/TripZip/assets/style.min.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap');
        .font-sans { font-family: 'Inter', sans-serif; }
    </style>
</head>
<body class="min-h-screen bg-background font-sans antialiased">
	<jsp:include page="/views/fragments/header.jsp" />
    

    <!-- Formulaire -->
    <main class="container px-4 py-8">
        <div class="mb-8 space-y-1">
            <h1 class="text-3xl font-semibold tracking-tight">Creation de Vol</h1>
            <p class="text-sm text-muted-foreground">Entrez les informations du vol</p>
        </div>
        
        <form action="/TripZip/vols" method="POST" id="volForm" class="space-y-8">
					<input type="hidden" name="idVol" value="0"/>
            <!-- Date du Vol -->
            <div class="space-y-2">
                <label class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70">
                    Date du Vol
                </label>
                <input 
                    type="datetime-local" 
                    name="dateVol"
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
                        required
                        class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 appearance-none"
                    >
                        <% for(models.vol.Ville v : (java.util.List<models.vol.Ville>) request.getAttribute("villes")) { %>
                            <option value="<%= v.getIdVille() %>"><%= v.getNom() %></option>
                        <% } %>
                    </select>
                </div>

                <div class="space-y-2">
                    <label class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70">
                        Ville d'Arrivee
                    </label>
                    <select 
                        name="villeArrivee" 
                        required
                        class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 appearance-none"
                    >
                        <% for(models.vol.Ville v : (java.util.List<models.vol.Ville>) request.getAttribute("villes")) { %>
                            <option value="<%= v.getIdVille() %>"><%= v.getNom() %></option>
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
                    required
                    class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 appearance-none"
                >
                    <% for(models.avion.Avion a : (java.util.List<models.avion.Avion>) request.getAttribute("avions")) { %>
                        <option value="<%= a.getIdAvion() %>"><%= a.getModele() %></option>
                    <% } %>
                </select>
            </div>

            <input type="hidden" name="reservation" value="1900-01-01T00:00" />
            <input type="hidden" name="annulation" value="1900-01-01T00:00" />

            <button 
                type="submit" 
                class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground hover:bg-primary/90 h-10 px-4 py-2 w-full"
            >
                Creer le vol
            </button>
        </form>
    </main>

    <script>
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