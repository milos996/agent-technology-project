import axios from "axios";

class HttpBaseClient {
  constructor() {
    this.client = axios.create({
      baseURL: "http://localhost:8080/AT2019/rest/managers",
      "Content-Type": "application/json",
      Accept: "application/json"
    });
  }

  attachHeaders(headers) {
    Object.assign(this.client.defaults.headers, headers);
  }

  detachHeader(headerKey) {
    delete this.client.defaults.headers[headerKey];
  }

  getApiClient() {
    return this.client;
  }
}

export default HttpBaseClient;
