import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8080",
});

API.interceptors.request.use((req) => {
  const token = localStorage.getItem("token");

  console.log("TOKEN SENT:", token);

  if (token && req.url !== "/auth/login" && req.url !== "/users" ) {
    req.headers.Authorization = `Bearer ${token}`;
  }

  return req;
});

export default API;