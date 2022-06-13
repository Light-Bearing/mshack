from flask import Flask, request
import ga_engine

app = Flask(__name__)

# allow both GET and POST requests

# в строке адреса браузера ввести http://127.0.0.1:5000/json

@app.route('/json', methods=['POST'])
def json():
    request_data = request.get_json()

    equation_inputs = request_data['equation_inputs']
    w, interest = ga_engine.calculate(equation_inputs)
    wts = w[0][0]
    weights = [0,0,0,0,0]
    sum = 0
    for i in range(0, len(wts)):
        sum = sum + wts[i]
    for j in range(0, len(wts)):
        weights[j] = wts[j]/sum
    res = str(weights)
    # return weights[0][0]

    return res


if __name__ == "__main__":
    app.run()












    # {
    #     "equation_inputs": [100000000, 4000000, 2000000, 600000, 600000]
    # }

    # {
    #   "equation_inputs": "[100000000, 4000000, 2000000, 600000, 600000]"
    #   "language": "Python",
    #   "framework": "Flask",
    #   "website": "Scotch",
    #   "version_info": {
    #     "python": "3.9.0",
    #     "flask": "1.1.2"
    #   },
    #   "examples": ["query", "form", "json"],
    #   "boolean_test": true
    # }

    # language = request_data['language']
    # framework = request_data['framework']
    # # two keys are needed because of the nested object
    # python_version = request_data['version_info']['python']
    #
    # # an index is needed because of the array
    # example = request_data['examples'][0]
    #
    # boolean_test = request_data['boolean_test']
    #
    # return '''
    #        The language value is: {}
    #        The framework value is: {}
    #        The Python version is: {}
    #        The item at index 0 in the example list is: {}
    #        The boolean value is: {}'''.format(language, framework, python_version, example, boolean_test)

@app.route("/")
def hello():
    # Inputs of the equation.
    equation_inputs = [100000000, 4000000, 2000000, 600000, 600000]
    weights, interest = ga_engine.calculate(equation_inputs)
    return str(weights[0][0])
    # return  "Hello World!"


# http://127.0.0.1:5000/json-example
@app.route('/json-example', methods=['GET', 'POST'])
def json_example():
    return 'JSON Object Example'


if __name__ == "__main__":
    app.run()