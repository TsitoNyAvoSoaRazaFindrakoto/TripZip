<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.vol.DetailsPlace" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Available Flights</title>
    	<link rel="stylesheet" href="/TripZip/assets/style.min.css">

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet" />
</head>
<body class="min-h-screen bg-gray-50 font-sans antialiased">
	<jsp:include page="/views/fragments/header.jsp" />
   

    <!-- Main Content -->
    <main class="container px-4 py-8">
        <!-- Header -->
        <div class="mb-8 space-y-1">
            <h1 class="text-3xl font-semibold">Available Flights</h1>
            <p class="text-gray-600">Book your next journey with us</p>
        </div>

        <!-- Flights Grid -->
        <div class="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
            <%
                List<DetailsPlace> vols = (List<DetailsPlace>) request.getAttribute("vols");
                if (vols != null) {
                    for (DetailsPlace vol : vols) {
            %>
            <div class="bg-white rounded-lg border p-6 hover:shadow-sm transition-shadow">
                <div class="flex flex-col space-y-4">
                    <!-- Flight Header -->
                    <div class="flex items-center justify-between">
                        <div>
                            <h3 class="font-semibold text-lg">
                                <%= vol.getVilleDepart().getNom() %> → <%= vol.getVilleArrivee().getNom() %>
                            </h3>
                            <p class="text-sm text-gray-500">
                                <%= vol.getAvion().getModele() %> • <%= vol.getDateVol() %>
                            </p>
                        </div>
                        <i class="fas fa-plane text-blue-500"></i>
                </div>

                    <!-- Pricing -->
                    <div class="space-y-2">
                        <div class="flex justify-between items-center">
                            <span class="text-gray-600">Economy</span>
                    <div class="flex items-center gap-2">
                        <% if (vol.getSiegesPromo() > 0) { %>
                            <span class="text-sm line-through text-gray-400">$<%= vol.getPrix() %></span>
                            <span class="text-blue-600 font-medium">$<%= vol.getPrixPromo() %></span>
                        <% } else { %>
                                    <span class="text-blue-600 font-medium">$<%= vol.getPrix() %></span>
                        <% } %>
                    </div>
                        </div>
                        
                        <div class="flex justify-between items-center">
                            <span class="text-gray-600">Available Seats</span>
                            <span class="font-medium"><%= vol.getDisponible() %></span>
                        </div>
                    </div>

                    <!-- Action Button -->
                    <a href="/TripZip/reservation?idSiegeVol=<%= vol.getIdSiegeVol() %>" class="w-full bg-blue-600 text-white py-2 px-4 rounded-lg text-center hover:bg-blue-700 transition-colors">
                        Book Now
                    </a>
                </div>
            </div>
            <%
                    }
                } else {
            %>
            <div class="col-span-full text-center py-12">
                <p class="text-gray-500">No flights available at the moment</p>
            </div>
            <%
                }
            %>
        </div>
    </main>
</body>
</html>