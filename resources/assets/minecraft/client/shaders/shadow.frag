#version 120

uniform sampler2D inTexture, textureToCheck;
uniform vec2 texelSize, direction;
uniform float radius;
uniform float weights[256];
uniform vec3 color;
#define offset texelSize * direction

void main() {
    vec2 st = gl_TexCoord[0].st;
    if (direction.y > 0 && texture2D(textureToCheck, gl_TexCoord[0].st).a != 0.0) discard;
    float blr = texture2D(inTexture, gl_TexCoord[0].st).a * weights[0];

    for (float f = 1.0; f <= radius; f++) {
        blr += texture2D(inTexture, gl_TexCoord[0].st + f * offset).a * (weights[int(abs(f))]);
        blr += texture2D(inTexture, gl_TexCoord[0].st - f * offset).a * (weights[int(abs(f))]);
    }
    gl_FragColor = vec4(color.r, color.g, color.b, blr);
}