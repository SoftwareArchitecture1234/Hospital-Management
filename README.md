Hướng dẫn chạy project:
1. Chạy server config-server
2. Chạy server discovery-server
3. Chạy service cần chạy.
-  Nếu cần test API của service hiện tại thì xem trong cấu hình tại:
  - config-server: config-server/resources/configurations/...
- Nếu cần test giao tiếp giữa các service với nhau thì mở các service cần test lên và gọi vào port service cần test
- Nếu cần test gateway thì mở gateway lên và gọi vào port gateway là 8080