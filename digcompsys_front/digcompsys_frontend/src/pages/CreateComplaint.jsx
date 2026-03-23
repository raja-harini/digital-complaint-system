import { useState } from "react";
import API from "../services/api";

function CreateComplaint() {
  const [formData, setFormData] = useState({
    title: "",
    description: "",
    category: "",
    priority: ""
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!formData.category || !formData.priority) {
      alert("Please select category and priority");
      return;
    }

    const payload = {
      ...formData
    };

    console.log("PAYLOAD:", payload);

    try {
      await API.post("/complaints", payload);
      alert("Complaint submitted successfully!");
    } catch (error) {
      console.error(error);
      alert("Error submitting complaint");
    }
  };

  return (
    <div className="container mt-5">
      <h2>Create Complaint</h2>

      <form onSubmit={handleSubmit}>

        <input
          type="text"
          name="title"
          placeholder="Title"
          className="form-control my-2"
          onChange={handleChange}
          required
        />

        <textarea
          name="description"
          placeholder="Description"
          className="form-control my-2"
          onChange={handleChange}
          required
        />

        <select
          name="category"
          className="form-control my-2"
          value={formData.category}
          onChange={handleChange}
          required
        >
          <option value="">Select Category</option>
          <option value="HARDWARE">HARDWARE</option>
          <option value="SOFTWARE">SOFTWARE</option>
          <option value="NETWORK">NETWORK</option>
          <option value="SECURITY">SECURITY</option>
          <option value="ELECTRICITY">ELECTRICITY</option>
          <option value="OTHER">OTHER</option>
        </select>

        <select
          name="priority"
          className="form-control my-2"
          value={formData.priority}
          onChange={handleChange}
          required
        >
          <option value="">Select Priority</option>
          <option value="LOW">LOW</option>
          <option value="MEDIUM">MEDIUM</option>
          <option value="HIGH">HIGH</option>
          <option value="URGENT">URGENT</option>
        </select>

        <button className="btn btn-primary mt-3">
          Submit Complaint
        </button>

      </form>
    </div>
  );
}

export default CreateComplaint;