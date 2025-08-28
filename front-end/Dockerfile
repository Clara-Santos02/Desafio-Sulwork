FROM node:18 AS build
WORKDIR /front-end

COPY package*.json ./
RUN npm install
COPY . .

RUN npx ng build --configuration production

FROM nginx:alpine

COPY --from=build /front-end/dist/desafio-mv /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]