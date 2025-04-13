from locust import HttpUser, task
class MyUser(HttpUser):
  @task
  def call_localhost(self):
    self.client.get("/calculateDefault")