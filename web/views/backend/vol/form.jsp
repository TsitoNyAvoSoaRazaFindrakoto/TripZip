<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Création de Vol</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
</head>
<body class="min-h-screen bg-background font-sans antialiased">
    <!-- Navbar similar to profil.jsp -->
    <header class="sticky top-0 z-50 w-full border-b bg-background/95 backdrop-blur">
        <div class="container flex h-16 items-center px-4">
            <div class="mr-6 hidden md:flex">
                <span class="text-lg font-semibold">TripZip</span>
            </div>
            <nav class="flex items-center space-x-6 text-sm font-medium">
                <a href="/TripZip/vols" class="transition-colors hover:text-foreground/80 text-foreground/60">
                    Flights
                </a>
                <a href="#" class="transition-colors hover:text-foreground/80 text-foreground/60">
                    Reservations
                </a>
            </nav>
            <div class="ml-auto flex items-center space-x-4">
                <div class="flex items-center gap-2">
                    <div class="h-8 w-8 rounded-full bg-muted flex items-center justify-center">
                        <i class="fas fa-user text-muted-foreground text-sm"></i>
                    </div>
                    <div class="hidden sm:block">
                        <p class="text-sm font-medium">john.doe@example.com</p>
                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Formulaire de création de Vol -->
    <main class="container px-4 py-8">
        <div class="mb-8 space-y-1">
            <h1 class="text-3xl font-semibold tracking-tight">Création de Vol</h1>
            <p class="text-muted-foreground">Entrez les informations du vol</p>
        </div>
        <form action="/TripZip/vols" method="POST" id="volForm" class="space-y-6">
            <div>
                <label for="dateVol" class="block text-sm font-medium text-foreground">Date du Vol</label>
                <input type="datetime-local" name="dateVol" id="dateVol" required
                 class="mt-1 block w-full rounded-md border border-gray-300 p-2" />
            </div>
            <div>
                <label for="villeDepart" class="block text-sm font-medium text-foreground">Ville de Départ</label>
                <select name="villeDepart" id="villeDepart" required class="mt-1 block w-full rounded-md border border-gray-300 p-2">
                    <%-- ...existing code for villes loop... --%>
                    <% for(models.vol.Ville v : (java.util.List<models.vol.Ville>) request.getAttribute("villes")) { %>
                        <option value="<%= v.getIdVille() %>"><%= v.getNom() %></option>
                    <% } %>
                </select>
            </div>
            <div>
                <label for="villeArrivee" class="block text-sm font-medium text-foreground">Ville d'Arrivée</label>
                <select name="villeArrivee" id="villeArrivee" required class="mt-1 block w-full rounded-md border border-gray-300 p-2">
                    <%-- ...existing code for villes loop... --%>
                    <% for(models.vol.Ville v : (java.util.List<models.vol.Ville>) request.getAttribute("villes")) { %>
                        <option value="<%= v.getIdVille() %>"><%= v.getNom() %></option>
                    <% } %>
                </select>
            </div>
            <div>
                <label for="idAvion" class="block text-sm font-medium text-foreground">Avion</label>
                <select name="idAvion" id="idAvion" required class="mt-1 block w-full rounded-md border border-gray-300 p-2">
                    <% for(models.avion.Avion a : (java.util.List<models.avion.Avion>) request.getAttribute("avions")) { %>
                        <option value="<%= a.getIdAvion() %>"><%= a.getModele() %></option>
                    <% } %>
                </select>
            </div>
            <input type="hidden" name="reservation" id="reservation" value="1900-01-01T00:00" />
            <input type="hidden" name="annulation" id="annulation" value="1900-01-01T00:00" />

            <button type="submit" class="mt-4 w-full rounded-md bg-primary py-2 text-sm font-semibold text-white hover:bg-primary-700">
                Créer le vol
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
