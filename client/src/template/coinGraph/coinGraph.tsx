import React, { useRef, useEffect } from "react";
import * as d3 from "d3";

interface CoinData {
  data: {
    date: string;
    volume: number;
    price: number;
  };
}

interface Props {
  coinData: CoinData[];
}

const CoinGraph: React.FC<Props> = ({ coinData }) => {
  const graphRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    // D3.js code to create the graph
    if (graphRef.current) {
      const svg = d3
        .select(graphRef.current)
        .append("svg")
        .attr("width", 500)
        .attr("height", 300);

      // Process and format the coin data
      const formattedData = coinData.map((data) => ({
        date: new Date(data.date), // Assuming date is in a valid format, you can adjust this based on your data structure
        volume: data.volume,
        price: data.price,
      }));

      // Set up scales for x and y axes
      const xScale = d3
        .scaleTime()
        .domain([
          d3.min(formattedData, (d) => d.date),
          d3.max(formattedData, (d) => d.date),
        ])
        .range([0, width]);

      const yScale = d3
        .scaleLinear()
        .domain([0, d3.max(formattedData, (d) => d.volume)])
        .range([height, 0]);

      // Create line or bar chart based on your preference
      const line = d3
        .line()
        .x((d) => xScale(d.date))
        .y((d) => yScale(d.volume));

      svg
        .append("path")
        .datum(formattedData)
        .attr("fill", "none")
        .attr("stroke", "steelblue")
        .attr("stroke-width", 2)
        .attr("d", line);

      // Add event listeners for mouseover to display additional information
      svg
        .selectAll("circle")
        .data(formattedData)
        .enter()
        .append("circle")
        .attr("cx", (d) => xScale(d.date))
        .attr("cy", (d) => yScale(d.volume))
        .attr("r", 4)
        .on("mouseover", (event, d) => {
          // Display additional information on mouseover
          console.log("Date:", d.date);
          console.log("Volume:", d.volume);
          console.log("Price:", d.price);
        });

      // Clean up and remove the graph when component unmounts
      return () => {
        svg.remove();
      };
    }
  }, [coinData]);

  return <div ref={graphRef}></div>;
};

export default CoinGraph;
