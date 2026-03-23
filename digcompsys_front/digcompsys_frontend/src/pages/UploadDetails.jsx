import { useState } from "react";
import API from "../services/api";

function UploadDetails() {
  const [field, setField] = useState("");
  const [role, setRole] = useState("");
  const [file, setFile] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!field) {
      alert("Please select field of work");
      return;
    }

    const formData = new FormData();
    formData.append("field", field);
    formData.append("role", role);
    formData.append("file", file);

    try {
      await API.post("/employee-details", formData, {
        headers: { "Content-Type": "multipart/form-data" }
      });

      alert("Details uploaded");

    } catch (err) {
      console.log(err);
      alert("Error");
    }
  };

  const handleUpload = async () => {
    if (!file) {
      alert("Please select a file");
      return;
    }

    const tokenData = JSON.parse(atob(localStorage.getItem("token").split(".")[1]));
    const userId = tokenData.userId;

    const formData = new FormData();
    formData.append("file", file);
    formData.append("userId", userId);

    try {
      await API.post("/users/upload", formData, {
        headers: {
          "Content-Type": "multipart/form-data"
        }
      });

      alert("Uploaded successfully");

    } catch (err) {
      console.log(err);
      alert("Upload failed");
    }
  };

  return (
    <div className="container mt-5">
      <h2>Upload Details</h2>

      <form onSubmit={handleSubmit}>

        <select
          className="form-control mb-3"
          value={field}
          onChange={(e) => setField(e.target.value)}
        >
          <option value="">-- Choose Field of Work --</option>
          <option value="HARDWARE">HARDWARE</option>
          <option value="SOFTWARE">SOFTWARE</option>
          <option value="NETWORK">NETWORK</option>
          <option value="SECURITY">SECURITY</option>
          <option value="ELECTRICITY">ELECTRICITY</option>
          <option value="OTHER">OTHER</option>
        </select>

        <input
          type="text"
          placeholder="Preferred Role"
          className="form-control mb-3"
          onChange={(e) => setRole(e.target.value)}
        />

        <input
          type="file"
          className="form-control mb-3"
          onChange={(e) => setFile(e.target.files[0])}
        />

        <button className="btn btn-success">Submit</button>

      </form>
    </div>
  );
}

export default UploadDetails;