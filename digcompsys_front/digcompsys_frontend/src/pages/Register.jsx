import { useState } from "react";
import API from "../services/api";
import { useNavigate } from "react-router-dom";

function Register() {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    userName: "",
    password: "",
    email: "",
    phone: "",
    roleName: "" // ✅ empty initially (important)
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleRegister = async (e) => {
    e.preventDefault();

    if (!formData.roleName) {
      alert("Please select a role");
      return;
    }

    try {
      const payload = {
        userName: formData.userName,
        password: formData.password,
        email: formData.email,
        phone: formData.phone,
        roleName: formData.roleName.toUpperCase()
      };

      console.log("SENDING:", payload);

      await API.post("/users", payload);

      alert("User registered successfully");
      navigate("/login");

    } catch (error) {
      console.log("FULL ERROR:", error);
      console.log("RESPONSE:", error.response);
      console.log("DATA:", error.response?.data);

      alert("Registration failed");
    }
  };

  return (
    <div className="container mt-5">
      <h2>Register</h2>

      <form onSubmit={handleRegister}>

        <input
          type="text"
          name="userName"
          placeholder="Username"
          className="form-control mb-3"
          onChange={handleChange}
          required
        />

        <input
          type="password"
          name="password"
          placeholder="Password"
          className="form-control mb-3"
          onChange={handleChange}
          required
        />

        <input
          type="email"
          name="email"
          placeholder="Email"
          className="form-control mb-3"
          onChange={handleChange}
          required
        />

        <input
          type="text"
          name="phone"
          placeholder="Phone"
          className="form-control mb-3"
          onChange={handleChange}
          required
        />

        {/* ✅ ROLE DROPDOWN WITH PLACEHOLDER */}
        <select
          name="roleName"
          className="form-control mb-3"
          onChange={handleChange}
          value={formData.roleName}
          required
        >
          <option value="" disabled hidden>
            Select Role
          </option>
          <option value="USER">User</option>
          <option value="EMPLOYEE">Employee</option>
        </select>

        <button className="btn btn-success w-100">Register</button>

      </form>
    </div>
  );
}

export default Register;