# Data Akun Sistem Perpustakaan Lib-Borrow

Berikut adalah daftar akun pengguna untuk Sistem Perpustakaan Lib-Borrow yang telah disusun rapi berdasarkan peran (*role*) masing-masing.

## 🔑 Role Admin

| No. | Email | Password |
| :--- | :--- | :--- |
| 1 | admin@mail.com | admin123 |

---

## 👥 Role Anggota

| No. | Email | Password |
| :--- | :--- | :--- |
| 1 | andi@mail.com | 12345 |
| 2 | arif@mail.com | 12345 |

> **Catatan:** Jagalah kerahasiaan data akun ini untuk mencegah penyalahgunaan hak akses sistem.
"""

file_path = "akun_lib_borrow.md"
with open(file_path, "w", encoding="utf-8") as file:
    file.write(markdown_content)

print(f"File successfully created: {file_path}")
