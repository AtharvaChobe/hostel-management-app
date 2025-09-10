import { jwtDecode } from "jwt-decode";

export function isTokenValid(token) {
  if (!token) return false;
  try {
    const decoded = jwtDecode(token);
    if (!decoded.exp) return false;
    return decoded.exp * 1000 > Date.now(); // check expiry
  } catch (e) {
    return false;
  }
}