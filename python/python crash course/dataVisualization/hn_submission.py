import requests

from operator import itemgetter

url = "https://hacker-news.firebaseio.com/v0/topstories.json"
r = requests.get(url)
print("响应码：", r.status_code)

submission_ids = r.json()
submission_dicts = []
for submission_id in submission_ids[:30]:
    url = ("https://hacker-news.firebaseio.com/v0/item/" + str(submission_id) + ".json")
    submission_r = requests.get(url)
    print(submission_r.status_code)
    requests_dict = submission_r.json()

    submission_dict = {
        "title": requests_dict["title"],
        "link": "http://news.ycombinator.com/item?id=" + str(submission_id),
        "comments": requests_dict.get("descendants", 0)
    }
    submission_dicts.append(submission_dict)

# itemgetter:在list的每个dict中提取关键字为comments的值作为排序key
submission_dicts = sorted(submission_dicts, key=itemgetter("comments"), reverse=True)

for submission_dict in submission_dicts:
    print("\nTitle:", submission_dict["title"])
    print("Discussion link:", submission_dict["link"])
    print("Comments:", submission_dict["comments"])
