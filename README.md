## Setup Instructions


1. **Build Docker Images**

   Navigate to the corresponding directories and run the following commands:

   ```bash
   docker build . -t moro-client
   docker build . -t moro-server
2. **Spin up client and server**

   ```bash
   docker compose -f moro-bundle.yml up
3. **To check client system resource info navigate to:**
   ```bash
   https://localhost:8088/systemResource
