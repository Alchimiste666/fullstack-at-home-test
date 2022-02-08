import axios from "axios";
import { get } from "lodash";

export function fetchArtists() {
  return axios
    .get("http://localhost:8080/artists")
    .then((response) => get(response, "data.content", []))
    .catch((error) => console.error(error));
}
