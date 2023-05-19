/* eslint-disable @typescript-eslint/no-non-null-assertion */
import React from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App";
import { BrowserRouter, HashRouter } from "react-router-dom";

const container = document.getElementById("root")!;
const root = createRoot(container);
root.render(
  <HashRouter>
    <App />
  </HashRouter>
);
