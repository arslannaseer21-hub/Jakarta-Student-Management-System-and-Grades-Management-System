<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">


</head>
<body>

    <div class="login-container">
        <h2>Login</h2>
        
        <button class="admin-btn selected" onclick="selectButton(this)">Admin Login</button>
        <button class="teacher-btn" onclick="selectButton(this)">Teacher Login</button>
        
        <form action="LoginServlet" method="post">
            <input type="hidden" id="userRole" name="userRole" value="admin"> <!-- Default to Admin -->
            <br>
            <input type="text" name="username" placeholder="Username" required>
            <br><br>
            <input type="password" name="password" placeholder="Password" required>
            <br><br>
            <button type="submit">Login</button>
        </form>
    </div>

    <script>
        // Function to handle button selection
        function selectButton(button) {
            // Remove 'selected' class from all buttons
            document.querySelectorAll('.admin-btn, .teacher-btn').forEach(btn => btn.classList.remove('selected'));
            // Add 'selected' class to clicked button
            button.classList.add('selected');
            // Set the hidden input value based on selection
            document.getElementById('userRole').value = button.innerText.includes('Admin') ? 'admin' : 'teacher';
        }
    </script>

</body>
</html>
