import { useState } from "react";
import API from "../services/api";
import { useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await API.post("/auth/login", {
        email,
        password,
      });

      console.log("LOGIN RESPONSE:", response.data);

      // ✅ Get token safely
      const token = response.data.token;

      if (!token) {
        console.error("❌ Token not found in response!");
        alert("Login failed: No token received");
        return;
      }

      // ✅ Store token
      localStorage.setItem("token", token);
      console.log("✅ Token stored:", localStorage.getItem("token"));

      // 🔓 Decode token
      const decoded = jwtDecode(token);
      console.log("DECODED TOKEN:", decoded);

      // ✅ Extract role & userId
      const role = decoded.role || response.data.role || "USER";
      const userId = decoded.userId || response.data.userId;

      // ✅ Store additional info
      localStorage.setItem("role", role);
      localStorage.setItem("userId", userId);

      console.log("ROLE:", role);
      console.log("USER ID:", userId);

      // 🚀 Redirect
      if (role === "ADMIN") {
        navigate("/admin");
      } else if (role === "USER") {
        navigate("/user");
      } else if (role === "EMPLOYEE") {
        navigate("/employee");
      } else {
        navigate("/");
      }

    } catch (error) {
      console.log("FULL ERROR:", error);
      console.log("RESPONSE:", error.response);
      console.log("DATA:", error.response?.data);
      alert("Invalid credentials");
    }
  };

  return (
    <div className="container mt-5">
      <h2>Login</h2>

      <form onSubmit={handleLogin}>
        <input
          type="text"
          placeholder="Email"
          className="form-control mb-3"
          onChange={(e) => setEmail(e.target.value)}
        />

        <input
          type="password"
          placeholder="Password"
          className="form-control mb-3"
          onChange={(e) => setPassword(e.target.value)}
        />

        <button className="btn btn-primary">Login</button>
      </form>
    </div>
  );
}

export default Login;