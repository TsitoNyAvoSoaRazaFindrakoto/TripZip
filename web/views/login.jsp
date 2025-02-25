<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login Page</title>
	<link rel="stylesheet" href="/TripZip/assets/style.min.css">
  <style>
    /* Custom scaling animation */
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
<body class="bg-gray-50 flex items-center justify-center min-h-screen">
  <div class="bg-white p-8 rounded-lg border border-gray-200 w-full max-w-md">
    <div class="text-center">
      <h1 class="text-2xl font-bold text-gray-900">Login</h1>
      <p class="text-gray-600 mt-2">Enter your credentials to continue</p>
    </div>

    <form class="mt-6 space-y-6" action="/TripZip/login" method="post">
      <div>
        <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
        <input
          type="email"
          id="email"
          name="email"
          placeholder="Enter your email"
          class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none  focus:ring-black focus:border-black"
          required
        />
      </div>
			

      <div>
        <label for="password" class="block text-sm font-medium text-gray-700">Password</label>
        <input
          type="password"
          id="password"
          name="mdp"
          placeholder="Enter your password"
          class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none  focus:ring-black focus:border-black"
          required
        />
      </div>

      <div>
        <button
          type="submit"
          class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md text-sm font-medium text-white bg-black hover:bg-gray-900 transition-all duration:300 ease-in-out transform active:animate-scale"
        >
          Sign in
        </button>
      </div>
    </form>
  </div>
</body>
</html>