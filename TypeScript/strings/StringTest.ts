usingTemplateLiterals();

// Template Literals
function usingTemplateLiterals() {
  const name: string = "Cristian";
  const lastName: string = "Peña";

  const greetingPerson = `Hola, ${name} ${lastName}`;
  console.log("templateLiteral:\r", greetingPerson);

  const multiline = `Hello
  ${name}
  -
  ${lastName}
  `;
  console.log("templateLiteral-MultiLine", multiline);
}

const templateLiteralMultiline = () => {};
