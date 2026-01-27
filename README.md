# Blogging-Platform-API

Pequeña API de ejemplo para un blog (Spring Boot + JPA + H2).
Provee CRUD sobre posts y un endpoint para listar categorías.

## Requisitos
- JDK 17+

## Endpoints

- `GET /api/post/{id}`
  - Descripción: obtiene un post por su id.
  - Body: ninguno.
  - Respuesta (200): `PostResponseDTO`:
    ```json
    {
      "id": 1,
      "title": "Título del post",
      "categoryName": "Tech",
      "content": "Contenido del post...",
      "tags": ["java","spring"],
      "createdAt": "2024-01-01T12:00:00Z",
      "updatedAt": "2024-01-02T12:00:00Z"
    }
    ```

- `POST /api/post`
  - Descripción: crea un nuevo post.
  - Body (JSON) — `PostRequestDTO`:
    ```json
    {
      "catName": "NombreCategoria",
      "title": "Título del post",
      "content": "Contenido del post (máx 500 caracteres)",
      "tags": ["tag1","tag2"]
    }
    ```
  - Respuesta (201): el `PostResponseDTO` creado y `Location` con `/api/post/{id}`.

- `PUT /api/post/{id}`
  - Descripción: actualiza un post existente.
  - Body (igual que `POST`): `PostRequestDTO`.
  - Respuesta (200): `PostResponseDTO` actualizado.

- `DELETE /api/post/{id}`
  - Descripción: elimina el post indicado.
  - Body: ninguno.
  - Respuesta: 204 No Content.

- `GET /api/category`
  - Descripción: lista todas las categorías.
  - Body: ninguno.
  - Respuesta (200): arreglo de categorías, cada una con campos:
    ```json
    {
      "id": 1,
      "name": "Tech"
    }
    ```

