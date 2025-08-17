<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.vol.DetailsPlace" %>
<%@ page import="models.Utilisateur" %>
<%
	DetailsPlace vol = (DetailsPlace) request.getAttribute("vol");
	if (vol == null) {
		response.sendRedirect("/TripZip/vols?error=flight_not_found");
		return;
	}
	Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
	int idUtilisateur = 0; // Default value in case utilisateur is null or idUtilisateur is not accessible
	if (utilisateur != null) {
		idUtilisateur = utilisateur.getIdUtilisateur();
	}
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Book your flight - TripZip</title>
    	<link rel="stylesheet" href="/TripZip/assets/style.min.css">

    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
      rel="stylesheet"
    />
  </head>
  <body class="min-h-screen bg-gray-50 font-sans antialiased">
    <header class="sticky top-0 z-50 w-full border-b bg-white backdrop-blur">
      <div class="container flex h-16 items-center px-4">
        <div class="mr-6">
          <a href="/TripZip/vols"
            ><span class="text-lg font-semibold">TripZip</span></a
          >
        </div>

        <nav class="flex items-center space-x-6 text-sm font-medium">
          <a href="/TripZip/vols" class="text-blue-600 hover:text-blue-800"
            >Flights</a
          >
          <a href="/TripZip/profil" class="text-gray-600 hover:text-gray-800"
            >Reservations</a
          >
        </nav>

        <div class="ml-auto flex items-center space-x-4">
          <div class="flex items-center gap-2">
            <div
              class="h-8 w-8 rounded-full bg-blue-100 flex items-center justify-center"
            >
              <i class="fas fa-user text-blue-500 text-sm"></i>
            </div>
            <div>
              <% if (utilisateur != null) { %>
              <p class="text-sm font-medium"><%= utilisateur.getEmail() %></p>
              <% } else { %>
              <p class="text-sm font-medium">Guest User</p>
              <% } %>
            </div>
          </div>
        </div>
      </div>
    </header>

    <main class="container px-4 py-8">
      <div class="mb-8 space-y-1">
        <h1 class="text-3xl font-semibold">Confirm your flight booking</h1>
        <p class="text-gray-500">
          Review your flight details and complete your reservation.
        </p>
      </div>

      <div class="bg-white rounded-lg border p-6 shadow-sm">
        <form action="/TripZip/reservation" method="post" class="space-y-6">
          <input
            type="hidden"
            name="idSiegeVol"
            value="<%= vol.getIdSiegeVol() %>"
          />
          <input
            type="hidden"
            name="idUtilisateur"
            value="<%= idUtilisateur %>"
          />
          <%-- Dynamically set from session --%>

          <div class="space-y-4 mb-6">
            <h2 class="text-xl font-semibold">Flight Details</h2>
            <div class="border rounded-lg p-4">
              <div class="flex items-center justify-between mb-2">
                <div>
                  <h3 class="font-semibold text-lg">
                    <%= vol.getVilleDepart().getNom() %> → <%=
                    vol.getVilleArrivee().getNom() %>
                  </h3>
                  <p class="text-sm text-gray-500">
                    <%= vol.getAvion().getModele() %> • <%= vol.getDateVol() %>
                  </p>
                </div>
                <i class="fas fa-plane text-blue-500"></i>
              </div>

              <div class="space-y-2">
                <div class="flex justify-between items-center">
                  <span class="text-gray-600">Economy Price</span>
                  <div class="flex items-center gap-2">
                    <% if (vol.getSiegesPromo() > 0) { %>
                    <span class="text-sm line-through text-gray-400"
                      >$<%= vol.getPrix() %></span
                    >
                    <span class="text-blue-600 font-medium"
                      >$<%= vol.getPrixPromo() %></span
                    >
                    <% } else { %>
                    <span class="text-blue-600 font-medium"
                      >$<%= vol.getPrix() %></span
                    >
                    <% } %>
                  </div>
                </div>
                <div class="flex justify-between items-center">
                  <span class="text-gray-600">Available Seats</span>
                  <span class="font-medium"><%= vol.getDisponible() %></span>
                </div>
              </div>
            </div>
          </div>

          <div>
						<div class="space-y-2">
							<label for="nombre" class="block text-sm font-medium text-gray-700"
								>Number of Seats</label
							>
							<div class="mt-1">
								<input
									type="number"
									name="nombre"
									id="nombre"
									min="1"
									max="<%= vol.getDisponible() %>"
									class="border rounded-lg p-4"
									placeholder="1"
									value="1"
									required
								/>
							</div>
							<p class="text-sm text-gray-500">
								Maximum available seats: <%= vol.getDisponible() %>
							</p>
						</div>
	
						<div class="space-y-2">
							<label for="dateReservation" class="block text-sm font-medium text-gray-700"
								>Reservation date</label
							>
							<div class="mt-1">
								<input
									type="datetime-local"
									name="dateReservation"
									id="dateReservation"
									class="border rounded-lg p-4"
									placeholder="1"
									required
								/>
							</div>
						</div>
					</div>

          <div>
            <button
              type="submit"
              class="w-full bg-blue-600 text-white py-2 px-4 rounded-lg text-center hover:bg-blue-700 transition-colors"
            >
              Confirm Reservation
            </button>
          </div>
        </form>
      </div>
    </main>
  </body>
</html>
