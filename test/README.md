## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Python 网页抓取

抓取网页标题、描述、正文文本和链接，并以 JSON 输出：

```sh
python3 scraper.py https://example.com
```

将结果保存到文件，并可调整请求超时：

```sh
python3 scraper.py https://example.com --timeout 15 --output result.json
```

抓取器只使用 Python 标准库。请遵守目标网站的服务条款、访问频率限制和 `robots.txt` 规则。

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
