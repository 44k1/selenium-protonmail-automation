# Automatización para Enviar Correo con Adjuntos en ProtonMail

Este proyecto contiene un test automatizado que abre el navegador Brave, inicia sesión en ProtonMail, redacta un correo, adjunta un archivo y lo envía.

---

## Qué hace

- Abre el navegador Brave.
- Inicia sesión en ProtonMail con usuario y contraseña.
- Redacta un nuevo correo.
- Llena el campo destinatario, asunto y cuerpo del mensaje.
- Adjunta un archivo desde tu computadora.
- Envía el correo.
- Verifica que el correo se haya enviado correctamente.
- Cierra el navegador.

---

## Qué necesitas

- Tener instalado Java (versión 11 o superior).
- Tener el navegador Brave instalado.
- Descargar el ChromeDriver compatible con Brave y colocarlo en `src/main/resources/chromedriver.exe`.
- Cambiar en el código el usuario y contraseña por los tuyos.
- Cambiar la ruta del archivo que quieres adjuntar.

---

## Cómo usarlo

1. Abre el archivo de test.
2. Cambia el usuario, contraseña y ruta del archivo en el código.
3. Ejecuta el test.
4. Verás cómo el navegador abre ProtonMail, escribe el correo y lo envía automáticamente.

---

## Recomendaciones

- Asegúrate que las rutas y credenciales sean correctas.
- El navegador se cerrará automáticamente al terminar.
- Puedes modificar el texto del mensaje en el código para personalizarlo.

---

¡Listo! Ahora puedes enviar correos con adjuntos usando esta automatización.

