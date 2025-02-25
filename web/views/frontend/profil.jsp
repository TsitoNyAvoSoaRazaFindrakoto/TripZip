<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.reservation.Reservation" %>
<%@ page import="models.vol.Vol" %>
<%@ page import="models.vol.Ville" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>My Reservations</title>
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
    

    <!-- Main Content -->
    <main class="container px-4 py-8">
        <!-- Header -->
        <div class="mb-8 space-y-1">
            <h1 class="text-3xl font-semibold tracking-tight">My Reservations</h1>
            <p class="text-muted-foreground">Manage your upcoming flights and bookings</p>
        </div>

        <!-- Reservations Grid -->
        <div class="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
            <%
                List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
                if (reservations != null) {
                    for (Reservation reservation : reservations) {
                        Vol vol = reservation.getSiegeVol().getVol();
                        Ville villeDepart = vol.getVilleDepart();
                        Ville villeArrivee = vol.getVilleArrivee();
            %>
            <div class="group relative rounded-lg border bg-card p-6 transition-shadow hover:shadow-sm">
                <div class="flex flex-col space-y-4">
                    <!-- Flight Header -->
                    <div class="flex items-center justify-between">
                        <div>
                            <h3 class="font-semibold leading-none tracking-tight">
                                <%= villeDepart.getNom() %> → <%= villeArrivee.getNom() %>
                            </h3>
                            <span class="text-sm text-muted-foreground">FLIGHT #<%= vol.getIdVol() %></span>
                        </div>
                        <i class="fas fa-plane text-muted-foreground"></i>
                    </div>

                    <!-- Flight Details -->
                    <div class="grid gap-2">
                        <div class="flex items-center justify-between">
                            <span class="text-sm text-muted-foreground">Departure</span>
                            <span class="text-sm font-medium"><%= vol.getDateVol() %></span>
                        </div>
                        <div class="flex items-center justify-between">
                            <span class="text-sm text-muted-foreground">Seats</span>
                            <span class="text-sm font-medium"><%= reservation.getNombre() %> seats</span>
                        </div>
                        <div class="flex items-center justify-between">
                            <span class="text-sm text-muted-foreground">Total</span>
                            <span class="text-sm font-medium">$<%= reservation.getPrix() %></span>
                        </div>
                    </div>

                    <!-- Status Badge -->
                    <div class="mt-4">
                        <span class="inline-flex items-center rounded-full bg-green-100 px-3 py-1 text-sm font-medium text-green-800">
                            Confirmed
                        </span>
                    </div>
                </div>
            </div>
            <%
                    }
                } else {
            %>
            <div class="flex h-[200px] w-full items-center justify-center rounded-md border border-dashed">
                <p class="text-sm text-muted-foreground">No reservations found</p>
            </div>
            <%
                }
            %>
        </div>
    </main>
</body>
</html>