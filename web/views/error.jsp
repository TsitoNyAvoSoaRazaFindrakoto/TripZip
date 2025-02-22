<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Page</title>
    <link rel="stylesheet" href="/TripZip/assets/style.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
</head>
<body class="bg-gray-50 flex items-center justify-center min-h-screen">
    <div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-3xl">
        <div class="text-center">
            <i class="fas fa-exclamation-triangle text-4xl text-red-500 mb-4"></i>
            <h1 class="text-3xl font-bold text-gray-900">Oops! Something Went Wrong</h1>
            <p class="text-gray-600 mt-2">We encountered an error while processing your request.</p>
        </div>
        <div class="mt-6 space-y-6">
            <% Exception e = (Exception)request.getAttribute("error"); %>
            <div class="border border-gray-200 rounded-md px-4 py-3 bg-gray-50">
                <small class="text-gray-600 block">Error Type:</small>
                <p class="text-lg font-bold text-gray-900"><%= e.getClass() %></p>
            </div>
            <div class="border border-gray-200 rounded-md px-4 py-3 bg-gray-50">
                <small class="text-gray-600 block">Error Message:</small>
                <p class="text-lg font-bold text-gray-900"><%= e.getMessage() %></p>
            </div>
            <div class="border border-gray-200 rounded-md px-4 py-3 bg-gray-50 overflow-y-auto max-h-48">
                <small class="text-gray-600 block">Stack Trace:</small>
                <% for (StackTraceElement ste : e.getStackTrace()) { %>
                    <p class="text-sm text-gray-700 font-mono"><%= ste.toString() %></p>
                <% } %>
            </div>
        </div>
        <div class="mt-6 text-center">
            <a href="/" class="inline-flex items-center px-4 py-2 bg-blue-500 text-white font-semibold rounded-lg hover:bg-blue-600 transition duration-300">
                <i class="fas fa-home mr-2"></i>
                Return to Home
            </a>
        </div>
    </div>
</body>
</html>