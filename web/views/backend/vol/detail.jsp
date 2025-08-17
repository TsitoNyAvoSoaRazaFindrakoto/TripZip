<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.vol.Vol" %>
<%@ page import="models.vol.Ville" %>
<%@ page import="models.vol.SiegeVol" %>
<%@ page import="models.avion.Avion" %>
<%@ page import="models.avion.SiegesAvions" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
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
    <!-- Navbar -->
    <header class="sticky top-0 z-50 w-full border-b bg-background/95 backdrop-blur">
        <div class="container flex h-16 items-center px-4">
            <!-- Logo -->
            <div class="mr-6 hidden md:flex">
                <span class="text-lg font-semibold">TripZip</span>
            </div>
            <!-- Navigation -->
            <nav class="flex items-center space-x-6 text-sm font-medium">
                <a href="/TripZip/vols" class="transition-colors hover:text-foreground/80 text-foreground/60">
                    Flights
                </a>
                <a href="#" class="transition-colors hover:text-foreground/80 text-foreground/60">
                    Reservations
                </a>
            </nav>
            <!-- Profile -->
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

    <main class="container px-4 py-8">
			<div class="mb-6 space-y-1">
				<h1 class="text-3xl font-semibold tracking-tight text-gray-800">Détails du Vol</h1>
				<p class="text-sm text-gray-600">Informations complètes du vol</p>
			</div>
		
			<%
				Vol vol = (Vol) request.getAttribute("vol");
				if (vol != null) {
					Avion avion = vol.getAvion();
					Ville villeDepart = vol.getVilleDepart();
					Ville villeArrivee = vol.getVilleArrivee();
			%>
		
			<div class="bg-gray-100 p-5 rounded-lg border border-gray-300">
				<div class="mb-4">
					<h2 class="text-xl font-semibold text-gray-800">Vol #<%= vol.getIdVol() %></h2>
				</div>
		
				<div class="grid grid-cols-1 md:grid-cols-2 gap-3">
					<div>
						<p class="text-sm text-gray-600">Date du Vol:</p>
						<p class="text-sm font-medium text-gray-800"><%= vol.getDateVol() %></p>
					</div>
					<div>
						<p class="text-sm text-gray-600">Avion:</p>
						<p class="text-sm font-medium text-gray-800"><%= avion != null ? avion.getModele() : "N/A" %></p>
					</div>
					<div>
						<p class="text-sm text-gray-600">Départ:</p>
						<p class="text-sm font-medium text-gray-800"><%= villeDepart != null ? villeDepart.getNom() : "N/A" %></p>
					</div>
					<div>
						<p class="text-sm text-gray-600">Arrivée:</p>
						<p class="text-sm font-medium text-gray-800"><%= villeArrivee != null ? villeArrivee.getNom() : "N/A" %></p>
					</div>
				</div>
		
				<%
					if (avion != null) {
						List<SiegesAvions> sieges = avion.getSieges();
						List<SiegeVol> siegesVol = vol.getSieges();
						// Create a map for quick lookup of SiegeVol by idSiege
						Map<Integer, SiegeVol> siegeVolMap = new HashMap<>();
						for (SiegeVol sv : siegesVol) {
							siegeVolMap.put(sv.getIdSiege(), sv);
						}
						
						if (sieges != null && !sieges.isEmpty()) {
				%>
				<div class="mt-6">
					<h3 class="text-lg font-semibold text-gray-800 mb-2">Sièges de l'avion:</h3>
					<div class="overflow-x-auto">
						<table class="table-auto w-full border-collapse border border-gray-300">
							<thead>
								<tr class="bg-gray-200 text-gray-800">
									<th class="border border-gray-300 px-3 py-2 text-left">Type de Siège</th>
									<th class="border border-gray-300 px-3 py-2 text-left">Nombre</th>
									<th class="border border-gray-300 px-3 py-2 text-left">Prix</th>
									<th class="border border-gray-300 px-3 py-2 text-left">Promotion</th>
									<th class="border border-gray-300 px-3 py-2 text-left">Actions</th>
								</tr>
							</thead>
							<tbody class="text-gray-700">
								<%
									for (SiegesAvions siege : sieges) {
										SiegeVol siegeVol = siegeVolMap.get(siege.getIdSiege());
								%>
								<tr class="bg-gray-50">
									<td class="border border-gray-300 px-3 py-2"><%= siege.getSiege().getNom() %></td>
									<td class="border border-gray-300 px-3 py-2"><%= siege.getNombre() %></td>
									<td class="border border-gray-300 px-3 py-2">
										<%= siegeVol != null && siegeVol.getMontant().doubleValue() > 0 ? siegeVol.getMontant() + " Ar" : "Non défini" %>
									</td>
									<td class="border border-gray-300 px-3 py-2">
										<%= siegeVol != null && siegeVol.getProm().doubleValue() > 0 ? siegeVol.getProm() + " %" : "Aucune" %>
									</td>
									<td class="border border-gray-300 px-3 py-2">
										<a href="/TripZip/vols/sieges/edit?idSiegeVol=<%=siegeVol.getIdSiegeVol()%>" 
											class="inline-flex items-center justify-center rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 bg-primary text-primary-foreground hover:bg-primary/90 h-9 px-4 py-2">
											<i class="fas fa-edit mr-2"></i>
											Modifier
										</a>
									</td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
				</div>
				<%
						} else {
				%>
				<div class="mt-6">
					<p class="text-sm text-gray-600">Aucun siège disponible pour cet avion.</p>
				</div>
				<%
						}
					}
				} else {
				%>
				<p class="text-sm text-gray-600">Aucun vol trouvé.</p>
				<%
				}
				%>
			</div>
    </main>    
</body>
</html>
