<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>TripZip - Travel Planning Made Simple</title>
	<link rel="stylesheet" href="/TripZip/assets/style.min.css">
  <style>
    @keyframes scale {
      0% { transform: scale(1); }
      50% { transform: scale(0.95); }
      100% { transform: scale(1); }
    }
    .animate-scale {
      animation: scale 0.2s ease-in-out;
    }
  </style>
</head>
<body class="bg-gray-50">
  <!-- Navigation -->
  <nav class="bg-white border-b border-gray-200">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <div class="flex justify-between h-16 items-center">
        <div class="flex-shrink-0">
          <span class="text-xl font-bold text-gray-900">TripZip</span>
        </div>
        <div class="hidden md:flex space-x-8">
          <a href="#" class="text-gray-600 hover:text-gray-900">Features</a>
          <a href="#" class="text-gray-600 hover:text-gray-900">Pricing</a>
          <a href="#" class="text-gray-600 hover:text-gray-900">Contact</a>
        </div>
      </div>
    </div>
  </nav>

  <!-- Hero Section -->
  <div class="bg-gray-50">
    <div class="max-w-7xl mx-auto py-16 px-4 sm:px-6 lg:px-8">
      <div class="text-center">
        <h1 class="text-4xl font-bold text-gray-900 sm:text-5xl md:text-6xl">
          Plan Your Perfect Trip
        </h1>
        <p class="mt-3 max-w-md mx-auto text-base text-gray-600 sm:text-lg md:mt-5 md:text-xl md:max-w-3xl">
          Organize your travel itinerary, collaborate with friends, and discover new destinations with TripZip.
        </p>
        <div class="mt-5 max-w-md mx-auto sm:flex sm:justify-center md:mt-8">
          <a href="#" class="inline-flex items-center px-6 py-3 border border-transparent text-base font-medium rounded-md text-white bg-black hover:bg-gray-900 transition-colors duration-300 transform active:animate-scale">
            Get Started
          </a>
        </div>
      </div>
    </div>
  </div>

  <!-- Features Section -->
  <div class="py-12 bg-white">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <div class="grid md:grid-cols-3 gap-8">
        <!-- Feature 1 -->
        <div class="p-6 border border-gray-200 rounded-lg">
          <div class="flex-shrink-0">
            <div class="h-12 w-12 bg-black rounded-md flex items-center justify-center">
              <svg class="h-6 w-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z"/>
              </svg>
            </div>
          </div>
          <h3 class="mt-4 text-xl font-semibold text-gray-900">Smart Itinerary Planning</h3>
          <p class="mt-2 text-gray-600">Create and organize your travel plans with our intuitive drag-and-drop interface.</p>
        </div>

        <!-- Feature 2 -->
        <div class="p-6 border border-gray-200 rounded-lg">
          <div class="flex-shrink-0">
            <div class="h-12 w-12 bg-black rounded-md flex items-center justify-center">
              <svg class="h-6 w-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z"/>
              </svg>
            </div>
          </div>
          <h3 class="mt-4 text-xl font-semibold text-gray-900">Collaborative Planning</h3>
          <p class="mt-2 text-gray-600">Invite friends and family to contribute to your travel plans in real-time.</p>
        </div>

        <!-- Feature 3 -->
        <div class="p-6 border border-gray-200 rounded-lg">
          <div class="flex-shrink-0">
            <div class="h-12 w-12 bg-black rounded-md flex items-center justify-center">
              <svg class="h-6 w-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z"/>
              </svg>
            </div>
          </div>
          <h3 class="mt-4 text-xl font-semibold text-gray-900">Destination Insights</h3>
          <p class="mt-2 text-gray-600">Get personalized recommendations based on your preferences and travel history.</p>
        </div>
      </div>
    </div>
  </div>

  <!-- CTA Section -->
  <div class="bg-gray-50">
    <div class="max-w-7xl mx-auto py-12 px-4 sm:px-6 lg:py-16 lg:px-8">
      <div class="bg-white border border-gray-200 rounded-lg p-8 text-center">
        <h2 class="text-3xl font-bold text-gray-900">Start Your Journey Today</h2>
        <p class="mt-4 text-gray-600">Join thousands of travelers already using TripZip to plan their perfect trips.</p>
        <div class="mt-6">
          <a href="#" class="inline-flex items-center px-6 py-3 border border-transparent text-base font-medium rounded-md text-white bg-black hover:bg-gray-900 transition-colors duration-300 transform active:animate-scale">
            Create Free Account
          </a>
        </div>
      </div>
    </div>
  </div>
</body>
</html>