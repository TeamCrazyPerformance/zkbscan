import React from "react";
import { Line } from "react-chartjs-2";
import { ChartOptions } from "chart.js";

interface CoinData {
  date: string;
  transactionCount: number;
  price: number;
}

interface ChartProps {
  data: CoinData[];
  xAxisLabel: string;
  yAxisLabel: string;
}

const ChartComponent: React.FC<ChartProps> = ({
  data,
  xAxisLabel,
  yAxisLabel,
}) => {
  const formattedData = data.map((item) => ({
    date: new Date(item.date),
    transactionCount: item.transactionCount,
    price: item.price,
  }));

  const labels = formattedData.map((item) =>
    item.date.toISOString().slice(0, 10)
  );
  const transactionCounts = formattedData.map((item) => item.transactionCount);

  const options: ChartOptions<"line"> = {
    plugins: {
      tooltip: {
        callbacks: {
          label: (tooltipItem): string => {
            const index = tooltipItem.dataIndex;
            const item = formattedData[index];
            return `${item.date.toISOString().slice(0, 10)} | Transactions: ${
              item.transactionCount
            } | Price: ${item.price}`;
          },
        },
      },
    },
    scales: {
      x: {
        title: {
          display: true,
          text: xAxisLabel,
        },
      },
      y: {
        title: {
          display: true,
          text: yAxisLabel,
        },
      },
    },
  };

  const chartDataConfig = {
    labels: labels,
    datasets: [
      {
        label: "Transaction Count",
        data: transactionCounts,
        borderColor: "blue",
        backgroundColor: "rgba(0, 0, 255, 0.2)",
      },
    ],
  };

  return <Line data={chartDataConfig} options={options} />;
};

export default ChartComponent;
