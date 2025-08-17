<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
	models.reservation.ConfigReservation configuration = (models.reservation.ConfigReservation) request.getAttribute("config");
%>
<html lang="fr">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Modifier Configuration de Réservation</title>
    	<link rel="stylesheet" href="/TripZip/assets/style.min.css">

  </head>
  <body class="min-h-screen bg-background font-sans antialiased">
    <jsp:include page="/views/fragments/header.jsp" />
    <main class="container px-4 py-8">
      <h1 class="text-3xl font-semibold tracking-tight mb-4">
        Modifier Configuration de Réservation
      </h1>
      <form action="/TripZip/config" method="POST" class="space-y-8">
        <!-- Champ libelle -->
        <div class="space-y-2">
          <label class="text-sm font-medium">Libellé : valeur actuelle</label>
          <input
            type="text"
            name="id"
						readonly
						value="<%=configuration.getId()%>"
						class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm"
          />
        </div>
        <!-- Champ durée -->
        <div class="space-y-2">
          <label class="text-sm font-medium">Durée (HH:MM)</label>
						<input
						type="time"
						name="value"
						value="<%=configuration.getValue()%>"
						required
						class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm"
						/>
        </div>
        <button
          type="submit"
          class="inline-flex items-center justify-center rounded-md text-sm font-medium bg-primary text-primary-foreground hover:bg-primary/90 h-10 px-4 py-2 w-full"
        >
          Mettre à jour
        </button>
      </form>
    </main>
  </body>
</html>
