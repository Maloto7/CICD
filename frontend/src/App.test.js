import { render, screen } from "@testing-library/react";
import App from "./App";

test("App", () => {
  render(<App />);
  const linkElement = screen.getByText("Welcome to the Encora demo");
  expect(linkElement).toBeInTheDocument();
});
