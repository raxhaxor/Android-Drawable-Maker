# Android - Drawable Maker
## Programmatically create and set shape drawable to any view

It is util which helps you to make shape drawable programmatically

- No need to write basic shape drawable, we can do it at runtime using this util.

## Example Usage

> Creating solid rectangle shape with 10dp corner radius and Light Gray color.

![N|Solid](https://i.ibb.co/5YGPRhw/Whats-App-Image-2021-02-21-at-2-44-31-PM.jpg)
```
btn.setDrawable {
            cornerRadius = 10
            color = Color.LTGRAY
        }
```


>Creating gradient(Left to Right) drawable with stroke(White) and dissimilar corner radius

![N|Solid](https://i.ibb.co/rGh6tdF/Whats-App-Image-2021-02-21-at-2-33-16-PM.jpg)
```
view.setDrawable {
            cornerRadii = CornerRadii(leftTop = 10, leftBottom = 20, rightTop =  10, rightBottom =  20)
            dashHeight = 2
            dashColor = Color.WHITE
            gradientOrientation = GradientDrawable.Orientation.LEFT_RIGHT
            gradientColors = intArrayOf(Color.RED, Color.CYAN, Color.GREEN)
        }
```

## Options
### We can make basic shape drawable with ease at runtime with these provided options.

- shape
- color
- cornerRadius
- cornerRadii
- gradientOrientation
- gradientColors
- dashHeight
- dashColor
- dashGap
- dashWidth
- gradientType
