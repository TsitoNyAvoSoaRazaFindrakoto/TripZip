<!-- Navbar -->
<header
  class="sticky top-0 z-50 w-full bg-gray-50 border-b border-gray-200"
>
  <div class="container flex h-16 items-center px-4">
    <!-- Logo -->
    <div class="mr-6 hidden md:flex">
      <span class="text-lg font-semibold">TripZip</span>
    </div>

    <!-- Navigation -->
    <nav class="flex items-center space-x-6 text-sm font-medium">
      <a
        href="/TripZip/vols"
        class="transition-colors hover:text-foreground/80 text-foreground/60"
      >
        Flights
      </a>
      <a
        href="#"
        class="transition-colors hover:text-foreground/80 text-foreground/60"
      >
        Reservations
      </a>
    </nav>

    <!-- Profile -->
    <div class="ml-auto flex items-center space-x-4">
      <div class="flex items-center gap-2">
        <div
          class="h-8 w-8 rounded-full bg-muted flex items-center justify-center"
        >
          <i class="fas fa-user text-muted-foreground text-sm"></i>
        </div>
        <div class="hidden sm:block">
          <p class="text-sm font-medium">john.doe@example.com</p>
        </div>
      </div>
    </div>
  </div>
</header>
