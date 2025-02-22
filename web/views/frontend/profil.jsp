<%@ page contentType="text/html;charset=UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>My Reservations</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
      rel="stylesheet"
    />
  </head>
  <body class="bg-gray-50 min-h-screen">
    <div class="container mx-auto px-4 py-8">
			<!-- Profile Section -->
      <div class="mb-8 bg-white rounded-lg shadow-sm p-6 border border-gray-300">
        <div class="flex items-center gap-4">
          <!-- Profile Picture -->
          <div class="w-12 h-12 rounded-full bg-blue-100 flex items-center justify-center">
            <i class="fas fa-user text-blue-500 text-xl"></i>
          </div>
          
          <!-- User Info -->
          <div>
            <h2 class="text-lg font-semibold text-gray-900">John Doe</h2>
            <p class="text-gray-600 text-sm">john.doe@example.com</p>
          </div>
        </div>
      </div>
      <div class="mb-8">
        <h1 class="text-3xl font-bold text-gray-900">My Reservations</h1>
        <p class="text-gray-600 mt-2">Here are your flight reservations</p>
      </div>

      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        <c:forEach items="${reservations}" var="reservation">
          <div class="bg-white rounded-lg shadow p-6">
            <div class="flex justify-between items-start mb-4">
              <div>
                <h3 class="text-lg font-semibold text-gray-900">
                  Flight #${reservation.siegeVol.vol.idVol}
                </h3>
                <p class="text-gray-500 text-sm">
                  ${reservation.siegeVol.vol.villeDepart.nom} →
                  ${reservation.siegeVol.vol.villeArrivee.nom}
                </p>
              </div>
              <i class="fas fa-plane-departure text-blue-500 text-xl"></i>
            </div>

            <div class="space-y-2">
              <div class="flex justify-between">
                <span class="text-gray-600">Departure:</span>
                <span class="text-gray-900">
                  ${reservation.siegeVol.vol.dateVol}
                </span>
              </div>

              <div class="flex justify-between">
                <span class="text-gray-600">Seats:</span>
                <span class="text-gray-900 font-medium">
                  ${reservation.nombre} seats
                </span>
              </div>

              <div class="flex justify-between">
                <span class="text-gray-600">Total Price:</span>
                <span class="text-blue-600 font-bold">
                  $${reservation.prix}
                </span>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </div>
  </body>
</html>
